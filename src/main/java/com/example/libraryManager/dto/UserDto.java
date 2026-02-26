package com.example.libraryManager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String name;
    private String prenom;
    private String email;
    private String password;
    private String role;

    public UserDto(String name, String prenom, String email, String password, String role) {
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
