package com.example.auth.Pet.Size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetSizeRepository extends JpaRepository<PetSize, Long> {
    PetSize findBySize(String name);
}
