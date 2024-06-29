package com.example.auth.Pet.Specie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, Long> {
    Specie findByName(String name);
}
