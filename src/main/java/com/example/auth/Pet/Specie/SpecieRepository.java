package com.example.auth.Pet.Specie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, Long> {

    @Query("select s.name from Specie s")
    List<String> getAllNames();
}
