package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Department;
import com.examly.springapp.model.Employee;
import com.examly.springapp.repository.DepartmentRepo;
import com.examly.springapp.repository.EmployeeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Employee addEmployee(Employee employee, int departmentId) {
        Optional<Department> optionalDepartment = departmentRepo.findById(departmentId);
        if(optionalDepartment.isEmpty()){
            throw new EntityNotFoundException("Department with department ID "+departmentId+" not found");
        }

        Department department = optionalDepartment.get();

        employee.setDepartment(department);

        Employee savedEmployee = employeeRepo.save(employee);

        return savedEmployee;

    }

    @Override
    public Employee updateEmployee(int employeeId, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
        if(optionalEmployee.isEmpty()){
            throw new EntityNotFoundException("Employee with ID "+employeeId+" not found");
        }

        Employee existingEmployee = optionalEmployee.get();

        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setDesignation(employee.getDesignation());
        
        Employee savedEmployee = employeeRepo.save(existingEmployee);
        return savedEmployee;
    }

    @Override
    public void deleteEmployee(int employeeId) {
        if(employeeRepo.existsById(employeeId)){
            employeeRepo.deleteById(employeeId);
        }else{
            throw new EntityNotFoundException("Employee with ID "+employeeId+" not found");
        }
    }

    @Override
    public List<Employee> getEmployeesSortedByNameAsc() {
        return employeeRepo.findAllOrderByEmployeeName();
    }

    @Override
    public List<Employee> getEmployeesSortedByNameDesc() {
        return employeeRepo.findAllOrderByEmployeeNameDesc();
    }


    
}
