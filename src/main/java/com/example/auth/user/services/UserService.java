package com.example.auth.user.services;


import com.example.auth.user.DTOs.UpdateDTO;
import com.example.auth.user.User;
import com.example.auth.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Integer id){
       User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User with such id not found"));
       return user;
    }

    public boolean isEmailRegistered(String email){
        return userRepository.existsByEmail(email);
    }

    public UserDetails save(User user){
        return userRepository.save(user);
    }

    public UserDetails findByEmail(String email) {
        UserDetails user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User with such email not found"));
        return user;
    }

    @Transactional
    public User updateUser(Integer userId, UpdateDTO dto){
        User user = this.findById(userId);

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhoneNumber(dto.phoneNumber());

        return userRepository.save(user);
    }
}
