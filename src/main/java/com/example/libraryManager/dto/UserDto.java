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
}
