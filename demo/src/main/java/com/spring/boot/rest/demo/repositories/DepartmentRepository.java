package com.spring.boot.rest.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.rest.demo.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
