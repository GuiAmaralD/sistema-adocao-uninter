package com.example.auth.user.controllers;


import com.example.auth.user.DTOs.RegisterUpdateDTO;
import com.example.auth.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("account")
public class UserAccountController {

    @Autowired
    private UserService userService;


    @GetMapping("/me")
    public ResponseEntity<UserDetails> getLoggedUserInfo(Principal principal){

        UserDetails user = userService.findByEmail(principal.getName());

        return ResponseEntity.ok().body(user);
    }

}
