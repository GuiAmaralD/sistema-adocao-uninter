package com.example.auth.user.DTOs;

import com.example.auth.user.UserRole;

public record RegisterDTO(

        String email,
        String password,
        UserRole role
) {
}
