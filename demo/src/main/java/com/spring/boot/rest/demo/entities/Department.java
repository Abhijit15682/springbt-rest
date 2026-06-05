package com.spring.boot.rest.demo.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    @JsonView(Views.DepartmentSummary.class) // Included in basic and detailed views
    private Integer deptId;

    @Column(name = "dept_name", length = 50, nullable = false)
    @JsonView(Views.DepartmentSummary.class) // Included in basic and detailed views
    private String deptName;

    // One department has many employees
    // mappedBy points to the 'department' field variable in the Employee class
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonView(Views.DepartmentDetailed.class) // ONLY included in the explicit detailed view
    private List<Employee> employees = new ArrayList<>();

    // Constructors
    public Department() {}

    public Department(String deptName) {
        this.deptName = deptName;
    }

    // Getters and Setters
    public Integer getDeptId() { return deptId; }
    public void setDeptId(Integer deptId) { this.deptId = deptId; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }

    // Helper methods to maintain bidirectional sync safely
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
    }
}
