package com.example.auth.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isEmailRegistered(String email){
        return userRepository.existsByEmail(email);
    }

    public UserDetails save(User user){
        return userRepository.save(user);
    }
}
