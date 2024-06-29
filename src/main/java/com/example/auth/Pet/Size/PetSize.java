package com.example.auth.Pet.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="petSize")
public class PetSize implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size")
    private String size;

    public PetSize(){

    }

    public PetSize(Long id, String name) {
        this.id = id;
        this.size = name;
    }

    public PetSize(String name){
        this.size = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return size;
    }

    public void setName(String name) {
        this.size = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetSize size = (PetSize) o;
        return Objects.equals(id, size.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
