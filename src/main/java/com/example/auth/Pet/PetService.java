package com.example.auth.Pet;


import com.example.auth.Pet.DTOs.SendPetToClientDTO;
import com.example.auth.user.User;
import com.example.auth.user.services.UserService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final UserService userService;

    public PetService(PetRepository petRepository, UserService userService) {
        this.petRepository = petRepository;
        this.userService = userService;
    }

    public List<Pet> findAllByAdoptedFalse(){
        List<Pet> pets = petRepository.findAllByAdoptedFalse();
        return pets;
    }

    public Pet findById(Long id){
        return petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Pet with such Id not found"));
    }

    public List<Pet> findPetsByCriteria(String specie, String sex, String size) {
        return petRepository.findAll((Specification<Pet>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(specie)) {
                predicates.add(criteriaBuilder.equal(root.get("specie").get("name"), specie));
            }
            if (StringUtils.hasText(sex)) {
                predicates.add(criteriaBuilder.equal(root.get("sex"), sex));
            }
            if (StringUtils.hasText(size)) {
                predicates.add(criteriaBuilder.equal(root.get("size").get("size"), size));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Transactional
    public Pet save(Pet pet){
        return petRepository.save(pet);
    }

    public boolean isPetFromLoggedUser(Long id, Principal principal){
        User user = (User) userService.findByEmail(principal.getName());

        Pet pet = this.findById(id);

        if(user.getRegisteredPets().contains(pet)){
            return true;
        }
        return false;
    }


    public SendPetToClientDTO toSendPetToClientDTO(Pet pet) {

        return new SendPetToClientDTO(
                pet.getId(),
                pet.getNickname(),
                pet.getSex(),
                pet.getSize(),
                pet.getSpecie(),
                pet.getDescription(),
                pet.isAdopted(),
                pet.getRegisteredAt(),
                pet.getUser()
        );
    }


}
