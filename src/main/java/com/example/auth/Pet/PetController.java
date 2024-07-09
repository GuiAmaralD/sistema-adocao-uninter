package com.example.auth.Pet;


import com.example.auth.Pet.DTOs.RegisterPetDTO;
import com.example.auth.Pet.DTOs.SendPetToClientDTO;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pet")
@CrossOrigin("*")
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
    public ResponseEntity<List<SendPetToClientDTO>> findAllByAdoptedFalse() {
        List<Pet> pets = petService.findAllByAdoptedFalse();
        List<SendPetToClientDTO> dtos = pets.stream()
                .map(petService::toSendPetToClientDTO) // Utilize o método de mapeamento que criamos antes
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SendPetToClientDTO> getPet(@PathVariable Long id) {
        Pet pet = petService.findById(id);

        SendPetToClientDTO dto = petService.toSendPetToClientDTO(pet);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Pet> registerNewPet(@ModelAttribute @Valid RegisterPetDTO dto,
                                              @RequestPart("file") MultipartFile file,
                                              Principal principal) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "imagem obrigatória");
        }

        String contentType = file.getContentType();
        if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "A imagem deve ser do tipo JPEG ou PNG.");
        }

        User user = (User) userService.findByEmail(principal.getName());

        Specie specie = specieRepository.findByName(dto.specie().toLowerCase());

        PetSize size = sizeRepository.findBySize(dto.size().toLowerCase());

        Pet pet = new Pet(null, dto.nickname(), dto.sex(), dto.description(), size,
                new Date(System.currentTimeMillis()), false, specie, user);

        // Salva a entidade Pet para obter o ID
        petService.save(pet);

        // Salva a imagem utilizando o ID do Pet
        String imagePath = petService.saveImage(file, pet.getId());

        // Você pode querer atribuir o caminho da imagem de volta à entidade Pet, se necessário
        pet.setImagePath(imagePath);
        petService.save(pet); // Atualiza a entidade Pet com o caminho da imagem, se necessário

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
        return "cron";
    }
}
