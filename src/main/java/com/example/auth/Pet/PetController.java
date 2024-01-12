package com.example.auth.Pet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping()
    public ResponseEntity<List<Pet>> findAll(){
        List<Pet> lista = petService.findAll();
        return ResponseEntity.ok().body(lista);
    }
}
