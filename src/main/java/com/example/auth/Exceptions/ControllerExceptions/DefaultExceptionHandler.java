package com.example.auth.Exceptions.ControllerExceptions;

import com.example.auth.Exceptions.CustomExceptions.ResourceNotFoundException;
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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        int code = HttpStatus.NOT_FOUND.value();
        String error = "resource not found";
        StandardError err = StandardError.init(code, error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(code).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> invalidInput(MethodArgumentNotValidException e, HttpServletRequest request){
        int code = HttpStatus.UNPROCESSABLE_ENTITY.value();
        String error = "invalid data input";
        StandardError err = StandardError.init(code, error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(code).body(err);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardError> handleAuthenticationException(AuthenticationException e, HttpServletRequest request){
        int code = HttpStatus.FORBIDDEN.value();
        String error = "Wrong email OR password";
        StandardError err = StandardError.init(code, error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(code).body(err);
    }
}
