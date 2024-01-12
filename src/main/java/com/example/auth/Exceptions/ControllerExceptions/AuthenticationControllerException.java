package com.example.auth.Exceptions.ControllerExceptions;


import com.example.auth.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.auth.Exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class AuthenticationControllerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        int code = HttpStatus.NOT_FOUND.value();
        String error = "resource not found";
        StandardError err = new StandardError(Instant.now(), code, error , e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(code).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> invalidInput(MethodArgumentNotValidException e, HttpServletRequest request){
        int code = HttpStatus.UNPROCESSABLE_ENTITY.value();
        String error = "invalid data input";
        StandardError err = new StandardError(Instant.now(), code, error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(code).body(err);
    }
}
