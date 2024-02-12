package com.example.auth.Pet.DTOs;

import com.example.auth.Pet.Specie.SpecieName;
import com.example.auth.Utils.EnumValidator.EnumValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterPetDTO(
        @NotBlank
        @Size(max = 30, message="max length for nickname = 255")
        @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "name should contain only letters")
        String nickname,
        @NotBlank
        @Size(max = 1, message = "pet sex cant have more than 1 character (m or f)")
        @Pattern(regexp = "^[Mm|Ff]{1}$", message ="only m or f allowed for sex property")
        String sex,
        @Size(max = 255, message="limit of 255 characters for description property")
        String description,
        @EnumValidator(
                enumClass = SpecieName.class,
                message = "nome de especie não é válido"
        )
        String specie
) {
}
