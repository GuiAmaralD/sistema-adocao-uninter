package com.example.auth.Pet.Specie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("specie")
public class SpecieController {

    @Autowired
    private SpecieRepository specieRepository;

    @GetMapping
    public ResponseEntity<List<String>> findAllSpecies(){
        List<String> list = specieRepository.getAllNames();

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity addSpecie(@RequestBody Map<String, String> json){
        String specieName = json.get("specie");
        if(specieName.matches(".*[0-9].*")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "specie number" +
                    "cannot contain any number");
        }

        if(specieRepository.getAllNames().contains(specieName)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "specie already " +
                    "registered");
        }

        specieRepository.save(new Specie(null, specieName));

        return ResponseEntity.ok().build();
    }
}
