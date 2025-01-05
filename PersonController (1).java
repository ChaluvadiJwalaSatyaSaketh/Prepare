package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.DuplicatePersonException;
import com.examly.springapp.model.Person;
import com.examly.springapp.service.PersonService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;


    @PostMapping("/person/passport/{passportId}")
    public ResponseEntity<?> addPerson(@PathVariable long passportId, @RequestBody Person person){
        try{
            Person savedPerson = personService.addPerson(passportId, person);
            return ResponseEntity.status(201).body(savedPerson);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/person")
    public ResponseEntity<?> addPerson(@RequestBody Person person){
        try{
            Person savedPerson = personService.addPerson(person);
            return ResponseEntity.status(201).body(savedPerson);
        }catch(DuplicatePersonException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/person/{personId}/passport/{passportId}")
    public ResponseEntity<?> linkPersonToPassport(@PathVariable long personId, @PathVariable long passportId){
        try{
            Person savedPerson = personService.linkPersonToPassport(personId, passportId);
            return ResponseEntity.status(200).body("Linked successfully");
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }


    @DeleteMapping("/person/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable long personId){
        try{
            personService.deletePerson(personId);
            return ResponseEntity.status(200).body(true);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(404).body(false);
        }
    }
    
}
