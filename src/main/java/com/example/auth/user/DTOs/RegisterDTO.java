package com.example.auth.user.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterDTO(

        @NotBlank(message = "name should not be blank")
        @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$", message = "name should contain only letters")
        @Size(max = 55)
        String name,
        @Size(min= 8, max=16, message = "invalid phone number length")
        @Pattern(regexp = "^[0-9]*$", message = "invalid phoneNumber entry")
        String phoneNumber,
        @Email(message = "email should be valid")
        @NotBlank(message = "email should not be blank")
        String email,
        @Size(max = 30, message = "maximum 30 characters allowed for password")
        String password
) {
}
