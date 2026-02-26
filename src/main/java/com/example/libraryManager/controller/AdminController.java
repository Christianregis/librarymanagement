package com.example.libraryManager.controller;

import com.example.libraryManager.dto.UserDto;
import com.example.libraryManager.model.User;
import com.example.libraryManager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<List<String>> deleteUser(@PathVariable Long id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(HttpStatus.OK).body(List.of("success","Utlisateur supprimee !"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(List.of("error","Utlisateur non present !"));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserInformation(@PathVariable Long id){
        if (userService.findUserById(id) != null){
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
        }
        return ResponseEntity.notFound().build();
    }


}
