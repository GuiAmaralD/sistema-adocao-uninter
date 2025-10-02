package com.example.auth.Pet;


import com.example.auth.Pet.DTOs.RegisterPetDTO;
import com.example.auth.Pet.DTOs.SendPetToClientDTO;
import com.example.auth.user.User;
import com.example.auth.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pet")
@CrossOrigin("*")
public class PetController {

    private final PetService petService;
    private final UserService userService;

    public PetController(PetService petService, UserService userService) {
        this.petService = petService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<SendPetToClientDTO>> findAllByAdoptedFalse() {
        List<Pet> pets = petService.findAllByAdoptedFalse();
        List<SendPetToClientDTO> dtos = pets.stream()
                .map(petService::toSendPetToClientDTO) // Utilize o método de mapeamento que criamos antes
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/filter")
    public List<Pet> getPetsByCriteria(
            @RequestParam(required = false) String specie,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) String size) {
        return petService.findPetsByCriteria(specie, sex, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SendPetToClientDTO> getPet(@PathVariable Long id) {
        Pet pet = petService.findById(id);

        SendPetToClientDTO dto = petService.toSendPetToClientDTO(pet);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Pet> registerNewPet(@RequestBody @Valid RegisterPetDTO dto,
                                              Principal principal) throws IOException {

        User user = (User) userService.findByEmail(principal.getName());

        Pet pet = new Pet(null, dto.nickname(), dto.sex(), dto.description(), dto.size(),
                new Date(System.currentTimeMillis()), false, dto.specie(), user);

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

    @GetMapping("/cron")
    public String cronJobMethod(){
        return "cronn";
    }
}
