package com.example.auth.Pet;


import com.example.auth.user.User;
import com.example.auth.user.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserService userService;

    public List<Pet> findAllByAdoptedFalse(){
        return petRepository.findAllByAdoptedFalse();
    }

    public Pet findById(Long id){
        return petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Pet with such Id not found"));
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
}
