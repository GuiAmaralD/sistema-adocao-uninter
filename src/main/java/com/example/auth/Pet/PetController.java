package com.example.auth.Pet;


import com.example.auth.Pet.DTOs.RegisterPetDTO;
import com.example.auth.Pet.Size.PetSize;
import com.example.auth.Pet.Size.PetSizeRepository;
import com.example.auth.Pet.Size.SizeName;
import com.example.auth.Pet.Specie.Specie;
import com.example.auth.Pet.Specie.SpecieName;
import com.example.auth.Pet.Specie.SpecieRepository;
import com.example.auth.user.User;
import com.example.auth.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private SpecieRepository specieRepository;

    @Autowired
    private PetSizeRepository sizeRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Pet>> findAllByAdoptedFalse(){
        List<Pet> lista = petService.findAllByAdoptedFalse();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> findPetById(@PathVariable Long id){
        Pet pet = petService.findById(id);

        return ResponseEntity.ok().body(pet);
    }

    @PostMapping
    public ResponseEntity<Pet> registerNewPet(@RequestBody @Valid RegisterPetDTO dto,
                                              Principal principal){
        User user = (User) userService.findByEmail(principal.getName());

        Specie specie = specieRepository.findByName(SpecieName.fromString(dto.specie()));

        PetSize size = sizeRepository.findBySizeName(SizeName.fromString(dto.size()));

        Pet pet = new Pet(null, dto.nickname(), dto.sex(), dto.description(), size,
                new Date(System.currentTimeMillis()), false, specie, user);

        petService.save(pet);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/adopted")
    public ResponseEntity<Pet> setAdoptedTrue(@PathVariable Long id, Principal principal){
        Pet pet = petService.findById(id);
        if(petService.isPetFromLoggedUser(id, principal)){
            pet.setAdopted(true);
            petService.save(pet);
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "You can only update your pets data");
        }
        return ResponseEntity.ok().build();
    }
}
