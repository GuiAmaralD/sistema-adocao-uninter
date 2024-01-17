package com.example.auth.Exceptions.ControllerExceptions;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.auth.Exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JWTExceptionHandler {

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<StandardError> invalidInput(JWTVerificationException e, HttpServletRequest request){
        StandardError err = StandardError
                .init(HttpStatusCode.valueOf(403), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(403).body(err);
    }
}
