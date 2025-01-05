package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer>{

    @Query("SELECT d FROM Department d WHERE d.departmentName LIKE :departmentName")
    public Optional<Department> findByDepartmentName(String departmentName);
    
}
