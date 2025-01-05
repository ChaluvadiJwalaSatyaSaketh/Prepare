package com.examly.springapp.exception;

public class DuplicatePassportException extends RuntimeException{

    public DuplicatePassportException(){

    }

    public DuplicatePassportException(String message){
        super(message);
    }
    
}
