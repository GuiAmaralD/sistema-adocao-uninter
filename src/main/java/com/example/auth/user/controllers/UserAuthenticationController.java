package com.example.auth.user.controllers;

import com.example.auth.user.DTOs.AuthenticationDTO;
import com.example.auth.infra.security.TokenService;
import com.example.auth.user.DTOs.RegisterDTO;
import com.example.auth.user.User;
import com.example.auth.user.UserRole;
import com.example.auth.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class UserAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    public UserAuthenticationController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid AuthenticationDTO data){
        Authentication auth;
        try{
             auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password()));
        }
        catch(UsernameNotFoundException | InternalAuthenticationServiceException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not registered", ex);
        }
        catch(BadCredentialsException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "authentication failure, bad credentials", ex);
        }

        String token = tokenService.generateToken((User) auth.getPrincipal());

        Map<String, String> jsonToken = new HashMap<>();
        jsonToken.put("token", token);

        return ResponseEntity.ok(jsonToken);
    }

    
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(userService.isEmailRegistered(data.email()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already registered");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(null, data.name(), data.email(), data.phoneNumber(), encryptedPassword, UserRole.USER);

        userService.save(newUser);

        return ResponseEntity.ok().build();
    }
}
