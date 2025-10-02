package com.example.auth.Exceptions.ControllerExceptions;

import com.example.auth.Exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class DefaultExceptionHandler{


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> invalidInput(MethodArgumentNotValidException e, HttpServletRequest request){
        StandardError err = StandardError
                .init(e.getStatusCode(), e.getFieldError().getDefaultMessage(), request.getRequestURI());

        return ResponseEntity.status(e.getStatusCode()).body(err);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardError> handleAuthenticationException(ResponseStatusException e, HttpServletRequest request){
        StandardError err = StandardError
                .init(e.getStatusCode(),  e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(e.getStatusCode()).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> invalidEnumValue(HttpMessageNotReadableException e, HttpServletRequest request){

        StandardError err = StandardError
                .init(HttpStatus.BAD_REQUEST,  e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(err.getStatus()).body(err);
    }


}
