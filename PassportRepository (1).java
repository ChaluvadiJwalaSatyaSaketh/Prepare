package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport,Long>{

    @Query("SELECT p FROM Passport p WHERE p.serialNumber LIKE :serialNumber")
    public Optional<Passport> findBySerialNumber(String serialNumber);
    
}
