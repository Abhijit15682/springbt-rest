package com.spring.boot.rest.demo.controller;

import com.spring.boot.rest.demo.dto.EmployeeDTO;
import com.spring.boot.rest.demo.entities.Department;
import com.spring.boot.rest.demo.entities.Employee;
import com.spring.boot.rest.demo.exception.ResourceNotFoundException;
import com.spring.boot.rest.demo.repositories.DepartmentRepository;
import com.spring.boot.rest.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
        return ResponseEntity.ok(convertToDTO(employee));
    }


    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return departmentRepository.findById(employeeDTO.getDeptId())
                .map(department -> {
                    Employee employee = new Employee(
                            employeeDTO.getFirstName(),
                            employeeDTO.getLastName(),
                            employeeDTO.getEmail()
                    );
                    employee.setDepartment(department);
                    Employee savedEmployee = employeeRepository.save(employee);
                    return ResponseEntity.ok(convertToDTO(savedEmployee));
                })
                .orElse(ResponseEntity.badRequest().body((EmployeeDTO) null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDetails) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    Department department = departmentRepository.findById(employeeDetails.getDeptId()).orElse(null);
                    if (department == null) {
                        return ResponseEntity.badRequest().body((EmployeeDTO) null);
                    }
                    employee.setFirstName(employeeDetails.getFirstName());
                    employee.setLastName(employeeDetails.getLastName());
                    employee.setEmail(employeeDetails.getEmail());
                    employee.setDepartment(department);
                    
                    Employee updatedEmployee = employeeRepository.save(employee);
                    return ResponseEntity.ok(convertToDTO(updatedEmployee));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Helper method to convert Entity to DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpId(employee.getEmpId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setHireDate(employee.getHireDate());
        if (employee.getDepartment() != null) {
            dto.setDeptId(employee.getDepartment().getDeptId()); // Links to Integer deptId
        }
        return dto;
    }
}
