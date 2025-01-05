package com.examly.springapp.service;

import com.examly.springapp.model.Person;

public interface PersonService {

    public Person addPerson(long passportId, Person person);
    public Person addPerson(Person person);
    public Person linkPersonToPassport(long personId,long passportId);
    public boolean deletePerson(long personId);
    public Person updatePerson(Person person,long personId);
    
}
