package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Department;

public interface DepartmentService {

    public Department addDepartment(Department department);
    public List<Department> getAllDepartments();
    public Department getDepartmentById(int departmentId);
    
    
}
