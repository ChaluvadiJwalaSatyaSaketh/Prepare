package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{

    @Query("SELECT p FROM Person p WHERE p.email LIKE :email")
    public Optional<Person> findByEmail(String email);
    
}
