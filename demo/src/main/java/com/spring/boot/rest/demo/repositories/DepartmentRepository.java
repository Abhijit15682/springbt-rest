package com.spring.boot.rest.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.spring.boot.rest.demo.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // LEFT JOIN FETCH pulls the department and all its employees in exactly 1 query
    //@Query("SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.employees")
    //List<Department> findAllWithEmployees();

    // Automatically handles the fetch join and structural deduplication cleanly
    @EntityGraph(attributePaths = {"employees"})
    @NonNull
    List<Department> findAll();
}
