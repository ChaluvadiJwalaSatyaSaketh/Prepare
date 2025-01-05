package com.examly.springapp.service;

import com.examly.springapp.model.Passport;

public interface PassportService {

    public Passport addPassport(Passport passport);
    public Passport addPassport(Passport passport, long personId);
    public boolean deletePassport(long passportId);
    public Passport updatePassport(Passport passport, long passportId);
    
}
