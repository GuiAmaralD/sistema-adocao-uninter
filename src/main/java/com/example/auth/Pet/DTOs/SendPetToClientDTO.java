package com.example.auth.Pet.DTOs;


import com.example.auth.Pet.enums.Sex;
import com.example.auth.Pet.enums.Size;
import com.example.auth.Pet.enums.Specie;
import com.example.auth.user.User;

import java.util.Date;

public record SendPetToClientDTO(
        Long id,
        String nickname,
        Sex sex,
        Size size,
        Specie specie,
        String description,
        boolean adopted,
        Date registeredAt,
        User user
){}
