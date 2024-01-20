package com.example.auth.Pet.Specie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name= "specie")
public class Specie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Specie(){

    }

    public Specie(Long id, String specieName) {
        this.id = id;
        this.name = specieName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecieName() {
        return name;
    }

    public void setSpecieName(String specieName) {
        this.name = specieName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specie especie = (Specie) o;
        return Objects.equals(id, especie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

