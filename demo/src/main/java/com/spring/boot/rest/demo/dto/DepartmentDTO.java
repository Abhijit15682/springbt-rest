package com.spring.boot.rest.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartmentDTO {
    private Integer deptId;

    @NotBlank(message = "Department name is required")
    @Size(max = 50, message = "Department name cannot exceed 50 characters")
    private String deptName;

    // Getters and Setters
    public Integer getDeptId() { return deptId; }
    public void setDeptId(Integer deptId) { this.deptId = deptId; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
}


