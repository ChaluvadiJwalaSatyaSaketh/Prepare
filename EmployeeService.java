package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Employee;


public interface EmployeeService {

    // POST  - /department/employee/{departmentId}
    public Employee addEmployee(Employee employee, int departmentId);

    // PUT   - /employee/{employeeId}
    public Employee updateEmployee(int employeeId,Employee employee);

    // DELETE - /employee/{employeeId}
    public void deleteEmployee(int employeeId);

    // GET  - /employee/name/asc
    public List<Employee> getEmployeesSortedByNameAsc();

    // GET  - /employee/name/desc
    public List<Employee> getEmployeesSortedByNameDesc();
    
}
