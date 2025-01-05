package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.DuplicatePassportException;
import com.examly.springapp.model.Passport;
import com.examly.springapp.service.PassportService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class PassportController {

    @Autowired
    private PassportService passportService;

    @PostMapping("/passport")
    public ResponseEntity<?> addPassport(@RequestBody Passport passport){
        try{
            Passport savedPassport = passportService.addPassport(passport);
            return ResponseEntity.status(201).body(savedPassport);
        }catch(DuplicatePassportException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/passport/person/{personId}")
    public ResponseEntity<?> addPassport(@PathVariable long personId, @RequestBody Passport passport){
        try{
            Passport savedPassport = passportService.addPassport(passport,personId);
            return ResponseEntity.status(201).body(savedPassport);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @DeleteMapping("/passport/{passportId}")
    public ResponseEntity<?> deletePassport(@PathVariable long passportId){
        try{
            passportService.deletePassport(passportId);
            return ResponseEntity.status(200).body(true);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body(false);
        }
    }


    @PutMapping("/passport/{passportId}")
    public ResponseEntity<?> updatePassport(@PathVariable long passportId, @RequestBody Passport passport){
        try{
            Passport savedPassport = passportService.updatePassport(passport, passportId);
            return ResponseEntity.status(200).body(savedPassport);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    
}
