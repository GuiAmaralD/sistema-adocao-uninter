package com.example.auth.Pet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping()
    public ResponseEntity<List<Pet>> findAllByAdoptedFalse(){
        List<Pet> lista = petService.findAllByAdoptedFalse();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Pet> findPetById(@PathVariable Long id){
        Pet pet = petService.findById(id);

        return ResponseEntity.ok().body(pet);
    }


}
