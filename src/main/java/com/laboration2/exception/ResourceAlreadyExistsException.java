package com.laboration2.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
