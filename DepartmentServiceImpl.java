package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicateDepartmentException;
import com.examly.springapp.model.Department;
import com.examly.springapp.repository.DepartmentRepo;

import jakarta.persistence.EntityNotFoundException;


@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Department addDepartment(Department department) {
        if(department.getDepartmentName() == null || department.getDepartmentName().trim().length() == 0 || department.getDepartmentName().length() > 50){
            throw new IllegalArgumentException("Invalid department name");
        }

        Optional<Department> optionalDepartment = departmentRepo.findByDepartmentName(department.getDepartmentName());
        if(optionalDepartment.isPresent()){
            throw new DuplicateDepartmentException("Department with department name "+department.getDepartmentName()+" already exists");
        }

        Department savedDepartment = departmentRepo.save(department);

        return savedDepartment;

    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departmentList = departmentRepo.findAll();
        if(departmentList.isEmpty()){
            throw new EntityNotFoundException("Department not found");
        }
        return departmentList;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        Optional<Department> optionalDepartment = departmentRepo.findById(departmentId);
        if(optionalDepartment.isEmpty()){
            throw new EntityNotFoundException("Departemtn with ID "+departmentId+" not found");
        }

        Department department = optionalDepartment.get();
        return department;
    }

    
    
}
