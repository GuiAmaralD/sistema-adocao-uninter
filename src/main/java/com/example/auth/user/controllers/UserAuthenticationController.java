package com.example.auth.user.controllers;

import com.example.auth.user.DTOs.AuthenticationDTO;
import com.example.auth.user.DTOs.LoginResponseDTO;
import com.example.auth.infra.security.TokenService;
import com.example.auth.user.DTOs.RegisterDTO;
import com.example.auth.user.User;
import com.example.auth.user.UserRole;
import com.example.auth.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("auth")
public class UserAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        Authentication auth;
        try{
             auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password()));
        }
        catch(BadCredentialsException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "authentication failure, bad credentials", ex);
        }
        catch(UsernameNotFoundException | InternalAuthenticationServiceException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not registered", ex);
        }

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
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
