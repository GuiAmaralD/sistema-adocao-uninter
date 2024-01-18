package com.example.auth.user.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordDto(
        @NotBlank(message = "oldpassword must not be blank")
        String oldPassword,
        @Size(max = 30, message = "maximum 30 characters allowed for password")
        @NotBlank(message = "new password must not be blank")
        String newPassword
) {
}
