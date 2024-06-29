package com.example.auth.user.controllers;


import com.example.auth.user.DTOs.ChangePasswordDTO;
import com.example.auth.user.DTOs.UpdateDTO;
import com.example.auth.user.User;
import com.example.auth.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("account")
@CrossOrigin("*")
public class UserAccountController {

    @Autowired
    private UserService userService;



    @GetMapping("/me")
    public ResponseEntity<UserDetails> getLoggedUserInfo(Principal principal){

        UserDetails user = userService.findByEmail(principal.getName());

        return ResponseEntity.ok().body(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody @Valid UpdateDTO updateDTO,
                                                  Principal principal){
        User user = (User) userService.findByEmail(principal.getName());

        return ResponseEntity.ok().body(userService.updateUser(user.getId(), updateDTO));

    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody @Valid ChangePasswordDTO dto, Principal principal){

        User user = (User) userService.findByEmail(principal.getName());
        Integer id = user.getId();

        userService.updatePassword(id, dto.oldPassword(), dto.newPassword());

        return ResponseEntity.ok().body("Password updated.");
    }
}
