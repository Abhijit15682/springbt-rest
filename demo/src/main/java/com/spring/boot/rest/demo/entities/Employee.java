package com.spring.boot.rest.demo.entities;


import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    @JsonView(Views.DepartmentDetailed.class) // Appears inside the department employee array
    private Integer empId;

    @Column(name = "first_name", length = 50, nullable = false)
    @JsonView(Views.DepartmentDetailed.class)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    @JsonView(Views.DepartmentDetailed.class)
    private String lastName;

    @Column(name = "email", length = 100, unique = true)
    @JsonView(Views.DepartmentDetailed.class)
    private String email;

    // Matches DEFAULT (CURRENT_DATE) behavior on creation
    @Column(name = "hire_date", insertable = false, updatable = false, columnDefinition = "DATE DEFAULT (CURRENT_DATE)")
    @JsonView(Views.DepartmentDetailed.class)
    private LocalDate hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id") // Matches your FOREIGN KEY (dept_id)
    @JsonManagedReference
    private Department department;

    // Constructors
    public Employee() {}

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters and Setters
    public Integer getEmpId() { return empId; }
    public void setEmpId(Integer empId) { this.empId = empId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
