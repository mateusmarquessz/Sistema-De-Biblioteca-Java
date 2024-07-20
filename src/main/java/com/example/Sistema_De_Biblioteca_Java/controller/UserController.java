package com.example.Sistema_De_Biblioteca_Java.controller;

import com.example.Sistema_De_Biblioteca_Java.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Sistema_De_Biblioteca_Java.service.UserService;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private UserService service;
    public UserController(UserService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    //Pega Todos os Usuarios
    @GetMapping()
    public List<Users> getAllUsers() {
        return service.findAll();
    }

    //Cria Usuario
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
    // Endpoints para criar, atualizar, deletar usuários, login, logout, etc.
}