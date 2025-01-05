package com.examly.springapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicatePersonException;
import com.examly.springapp.model.Passport;
import com.examly.springapp.model.Person;
import com.examly.springapp.repository.PassportRepository;
import com.examly.springapp.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PassportRepository passportRepository;

    @Override
    public Person addPerson(long passportId, Person person) {

        Optional<Passport> optPassport = passportRepository.findById(passportId);
        if(optPassport.isEmpty()){
            throw new EntityNotFoundException("Passort not found with ID "+passportId);
        }

        Passport passport = optPassport.get();

        if(passport.getPerson() != null){
            throw new IllegalArgumentException("This passort is already assigned");
        }

        person.setPassport(passport);

        Person savedPerson = personRepository.save(person);
        return savedPerson;
        
    }

    public Person addPerson(Person person){
        Optional<Person> optPerson = personRepository.findByEmail(person.getEmail());
        if(optPerson.isPresent()){
            throw new DuplicatePersonException("Person with email "+person.getEmail()+" already exists");
        }

        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }

    @Override
    public Person linkPersonToPassport(long personId, long passportId) {
        Optional<Person> optPerson = personRepository.findById(personId);
        Optional<Passport> optPassport = passportRepository.findById(passportId);

        if(optPerson.isEmpty()){
            throw new EntityNotFoundException("Person with ID "+personId+" not found");
        }

        if(optPassport.isEmpty()){
            throw new EntityNotFoundException("Passport with ID "+passportId+" not found");
        }

        Person person = optPerson.get();
        Passport passport = optPassport.get();

        if(person.getPassport() != null){
            throw new IllegalArgumentException("Person with Id "+personId+" is already linked with other passport");
        }

        person.setPassport(passport);

        Person savedPerson = personRepository.save(person);

        return savedPerson;
    }

    @Override
    public boolean deletePerson(long personId) {
        if(personRepository.existsById(personId)){
            personRepository.deleteById(personId);
            return true;
        }else{
            throw new EntityNotFoundException("Person with ID "+personId+" not found");
        }
    }

    @Override
    public Person updatePerson(Person person, long personId) {
        Optional<Person> optPerson = personRepository.findById(personId);
        if(optPerson.isEmpty()){
            throw new EntityNotFoundException("Person with ID "+personId+" not found");
        }

        Person existingPerson = optPerson.get();

        existingPerson.setName(person.getName());
        existingPerson.setDateOfBirth(person.getDateOfBirth());
        existingPerson.setEmail(person.getEmail());
        existingPerson.setPhoneNumber(person.getPhoneNumber());

        Person savedPerson = personRepository.save(existingPerson);
        return savedPerson;
    }
    
}
