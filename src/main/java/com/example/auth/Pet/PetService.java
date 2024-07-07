package com.example.auth.Pet;


import com.example.auth.Pet.DTOs.SendPetToClientDTO;
import com.example.auth.user.User;
import com.example.auth.user.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserService userService;

    public List<Pet> findAllByAdoptedFalse(){
        List<Pet> pets = petRepository.findAllByAdoptedFalse();
        return pets;
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

    public String saveImage(MultipartFile imageFile, Long petId) throws IOException {
        // Diretório onde as imagens serão armazenadas (configurado no seu ambiente)
        String uploadDir = "src/main/resources/static/images/";

        // Gerar nome de arquivo único
        String fileName = "pet_image_" + petId + "_" + UUID.randomUUID().toString() + ".jpg";

        // Construir o caminho completo do arquivo
        Path filePath = Paths.get(uploadDir, fileName);

        // Certificar-se de que o diretório pai existe
        Files.createDirectories(filePath.getParent());

        // Salvar o arquivo no sistema de arquivos
        try (InputStream inputStream = imageFile.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Retornar o caminho relativo do arquivo
        return Paths.get(fileName).toString();
    }

    public SendPetToClientDTO toSendPetToClientDTO(Pet pet) {
        String base64Image = "";
        String basePath = "src/main/resources/static/images/";
        if (pet.getImagePath() != null) {
            try {
                byte[] imageBytes = Files.readAllBytes(Paths.get(basePath + pet.getImagePath()));
                base64Image = Base64.getEncoder().encodeToString(imageBytes);
            } catch (IOException e) {
                e.printStackTrace(); // Trate a exceção de forma adequada
            }
        }
        return new SendPetToClientDTO(
                pet.getId(),
                pet.getNickname(),
                pet.getSex(),
                pet.getSize(),
                pet.getSpecie(),
                pet.getDescription(),
                pet.isAdopted(),
                pet.getRegisteredAt(),
                base64Image,
                pet.getUser()
        );
    }


}
