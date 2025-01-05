package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.DuplicateDepartmentException;
import com.examly.springapp.model.Department;
import com.examly.springapp.service.DepartmentService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    // /POST - /department
    @PostMapping("/department")
    public ResponseEntity<?> addDepartment(@RequestBody Department department){
        try{
            Department savedDepartment = departmentService.addDepartment(department);
            return ResponseEntity.status(201).body(savedDepartment);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }catch(DuplicateDepartmentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    // /GET  - /department

    @GetMapping("/department")
    public ResponseEntity<?> getAllDepartments(){
        try{
            List<Department> departmentList = departmentService.getAllDepartments();
            return ResponseEntity.status(200).body(departmentList);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    // /GET  - /department/{departmentId}

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<?> getDepartmentById(@PathVariable int departmentId){
        try{
            Department department = departmentService.getDepartmentById(departmentId);
            return ResponseEntity.status(200).body(department);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
