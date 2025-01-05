package com.examly.springapp.exception;

public class DuplicatePersonException extends RuntimeException{

    public DuplicatePersonException(){

    }

    public DuplicatePersonException(String message){
        super(message);
    }
    
}
