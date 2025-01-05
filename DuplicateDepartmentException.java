package com.examly.springapp.exception;

public class DuplicateDepartmentException extends RuntimeException{

    public DuplicateDepartmentException(){

    }

    public DuplicateDepartmentException(String message){
        super(message);
    }
    
}
