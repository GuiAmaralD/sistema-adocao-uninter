package com.example.auth.Exceptions.ControllerExceptions;

import com.example.auth.Exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> invalidInput(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String error = "invalid data input";
        StandardError err = StandardError.init(status, error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardError> handleAuthenticationException(AuthenticationException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.FORBIDDEN;
        String error = "Authentication error";
        StandardError err = StandardError.init(status, error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
