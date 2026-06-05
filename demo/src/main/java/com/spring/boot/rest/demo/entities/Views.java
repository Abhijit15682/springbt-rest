package com.spring.boot.rest.demo.entities;

public class Views {
    // Shows only basic Department details
    public interface DepartmentSummary {}

    // Shows Department details AND its list of Employees
    public interface DepartmentDetailed extends DepartmentSummary {}
}
