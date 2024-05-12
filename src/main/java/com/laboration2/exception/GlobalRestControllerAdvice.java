package com.laboration2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException e){
        return new ResponseEntity<>("Resource not found: "+ e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleResourceAlreadyExists(ResourceAlreadyExistsException e){
        return new ResponseEntity<>("Resource already exists: "+ e.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<String> handleNotAuthenticated(NotAuthenticatedException e){
        return new ResponseEntity<>("User is not Authenticated: "+ e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
