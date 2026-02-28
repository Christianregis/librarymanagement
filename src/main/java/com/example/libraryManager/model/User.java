package com.example.libraryManager.model;

import com.example.libraryManager.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String prenom;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private LocalDate dateInscription = LocalDate.now();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Emprunt> emprunts;

    public UserDto toDto(){
        return new UserDto(getName(), getPrenom(), getEmail(), getPassword(), getRole(), getId());
    }
}
