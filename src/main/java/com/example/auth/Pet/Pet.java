package com.example.auth.Pet;


import com.example.auth.Pet.Size.PetSize;
import com.example.auth.Pet.Specie.Specie;
import com.example.auth.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name= "pet")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String sex;
    private String description;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date registeredAt;

    private boolean adopted;

    @ManyToOne
    private Specie specie;

    @ManyToOne
    private PetSize size;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Pet(){

    }

    public Pet(Long id, String nickname, String sex, String description, PetSize size, Date registeredAt, boolean adopted, Specie specie, User user) {
        this.id = id;
        this.nickname = nickname;
        this.sex = sex;
        this.description = description;
        this.size = size;
        this.registeredAt = registeredAt;
        this.adopted = adopted;
        this.specie = specie;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public PetSize getSize() {
        return size;
    }

    public void setSize(PetSize size) {
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(nickname, pet.nickname) && Objects.equals(sex, pet.sex) && Objects.equals(description, pet.description) && Objects.equals(specie, pet.specie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, sex, description, specie);
    }
}
