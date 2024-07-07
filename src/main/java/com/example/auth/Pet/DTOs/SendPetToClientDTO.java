package com.example.auth.Pet.DTOs;

import com.example.auth.Pet.Size.PetSize;
import com.example.auth.Pet.Specie.Specie;
import com.example.auth.user.User;

import java.util.Date;

public record SendPetToClientDTO(
        Long id,
        String nickname,
        String sex,
        PetSize size,
        Specie specie,
        String description,
        boolean adopted,
        Date registeredAt,
        String imageBase64,
        User user
){}
