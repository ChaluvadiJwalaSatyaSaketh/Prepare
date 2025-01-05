package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Department;
import com.examly.springapp.model.Employee;
import com.examly.springapp.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    // POST  - /department/employee/{departmentId}

    @PostMapping("/department/employee/{departmentId}")
    public ResponseEntity<?> addEmployee(@PathVariable int departmentId, @RequestBody Employee employee){
        try{
            Employee savedEmployee = employeeService.addEmployee(employee, departmentId);
            return ResponseEntity.status(201).body(savedEmployee);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // PUT   - /employee/{employeeId}
    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee){
        try{
            Employee savedEmployee = employeeService.updateEmployee(employeeId, employee);
            return ResponseEntity.status(200).body(savedEmployee);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // DELETE - /employee/{employeeId}
    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId){
        try{
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.status(200).body("Employee deleted successfully");
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body("Employee with ID "+employeeId+" not found");
        }
    }

    // GET  - /employee/sorted/asc
    @GetMapping("/employee/sorted/asc")
    public ResponseEntity<?> getEmployeesSortedByNameAsc(){
        List<Employee> employeeList = employeeService.getEmployeesSortedByNameAsc();
        return ResponseEntity.status(200).body(employeeList);
    }

    // GET  - /employee/sorted/desc
    @GetMapping("/employee/sorted/desc")
    public ResponseEntity<?> getEmployeesSortedByNameDesc(){
        List<Employee> employeeList = employeeService.getEmployeesSortedByNameDesc();
        return ResponseEntity.status(200).body(employeeList);
    }
}
