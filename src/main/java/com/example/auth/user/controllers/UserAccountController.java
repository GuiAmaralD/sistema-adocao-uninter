package com.example.auth.user.controllers;


import com.example.auth.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class UserAccountController {

    @Autowired
    private UserService userService;

    @PutMapping
    public String updateUser(){
        System.out.println("21412412");
        return "metodo realizado";
    }
}
