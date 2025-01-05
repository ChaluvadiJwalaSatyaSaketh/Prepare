package com.examly.springapp.service;

import java.util.Optional;

import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicatePassportException;
import com.examly.springapp.model.Passport;
import com.examly.springapp.model.Person;
import com.examly.springapp.repository.PassportRepository;
import com.examly.springapp.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PassportServiceImpl implements PassportService{

    @Autowired
    private PassportRepository passportRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Passport addPassport(Passport passport) {
        if(passport.getSerialNumber() == null || passport.getCountry() == null || passport.getSerialNumber().trim().length() == 0 ||  passport.getCountry().trim().length() == 0 || passport.getCountry().length() >50)
            throw new IllegalArgumentException("Passport data invalid");

        Optional<Passport> optPassport = passportRepository.findBySerialNumber(passport.getSerialNumber());
        if(optPassport.isPresent()){
            throw new DuplicatePassportException("Passport with serial number "+passport.getSerialNumber()+" already exists");
        }

        Passport savedPassport = passportRepository.save(passport);
        return savedPassport;
    }

    @Override
    public Passport addPassport(Passport passport, long personId) {
        Optional<Person> optPerson = personRepository.findById(personId);
        if(optPerson.isEmpty()){
            throw new EntityNotFoundException("Person with ID "+personId+" not found");
        }

        Person person = optPerson.get();

        if(person.getPassport() != null){
            throw new IllegalArgumentException("Person is already have passport");
        }

        Passport savedPassport = passportRepository.save(passport);

        person.setPassport(savedPassport);
        personRepository.save(person);

        return savedPassport;
    }

    @Override
    public boolean deletePassport(long passportId) {
        if(passportRepository.existsById(passportId)){
            passportRepository.deleteById(passportId);
            return true;
        }else{
            throw new EntityNotFoundException("Passport with ID "+passportId+" not found");
        }
    }

    @Override
    public Passport updatePassport(Passport passport, long passportId) {
        Optional<Passport> optPassport = passportRepository.findById(passportId);
        if(optPassport.isEmpty()){
            throw new EntityNotFoundException("Passport with ID "+passportId+" not found");
        }

        Passport existingPassport = optPassport.get();

        existingPassport.setSerialNumber(passport.getSerialNumber());
        existingPassport.setIssueYear(passport.getIssueYear());
        existingPassport.setCountry(passport.getCountry());

        Passport savedPassport = passportRepository.save(existingPassport);
        return savedPassport;

    }
    
}
