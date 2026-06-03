package com.spring.boot.rest.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.rest.demo.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Custom finder method to query employees by department
    List<Employee> findByDepartmentDeptId(Integer departmentId);
}
