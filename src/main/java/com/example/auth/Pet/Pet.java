package com.example.auth.Pet;


import com.example.auth.Pet.enums.Sex;
import com.example.auth.Pet.enums.Size;
import com.example.auth.Pet.enums.Specie;
import com.example.auth.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
    private String description;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date registeredAt;

    private boolean adopted;

    @Enumerated(EnumType.STRING)
    private Specie specie;

    @Enumerated(EnumType.STRING)
    private Size size;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "imagepath")
    private String imagePath;


    public Pet(){

    }

    public Pet(Long id, String nickname, Sex sex, String description, Size size, Date registeredAt, boolean adopted, Specie specie, User user) {
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

    public Pet(Long id, String nickname, Sex sex, String description, Size size, Date registeredAt, boolean adopted, Specie specie, User user, String imagePath) {
        this.id = id;
        this.nickname = nickname;
        this.sex = sex;
        this.description = description;
        this.size = size;
        this.registeredAt = registeredAt;
        this.adopted = adopted;
        this.specie = specie;
        this.user = user;
        this.imagePath = imagePath;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
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

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", description='" + description + '\'' +
                ", registeredAt=" + registeredAt +
                ", adopted=" + adopted +
                ", specie=" + specie +
                ", size=" + size +
                ", user=" + user +
                '}';
    }
}
