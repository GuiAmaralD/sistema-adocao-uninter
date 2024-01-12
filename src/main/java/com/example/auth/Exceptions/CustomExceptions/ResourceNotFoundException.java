package com.example.auth.Exceptions.CustomExceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
