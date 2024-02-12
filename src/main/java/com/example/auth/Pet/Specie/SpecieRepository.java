package com.example.auth.Pet.Specie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecieRepository extends JpaRepository<Specie, Long> {

    Specie findByName(SpecieName name);
}
