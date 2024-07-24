package com.example.Sistema_De_Biblioteca_Java.controller;

import com.example.Sistema_De_Biblioteca_Java.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Sistema_De_Biblioteca_Java.service.UserService;

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

    //Pega o Usuario pelo ID
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable long id) {
        return service.findById(id);
    }

    //Cria Usuario
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    //Deletando Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //Atualizando nome Usuario
    @PutMapping("/name/{id}")
    public ResponseEntity<Users> updateUserName(@PathVariable long id, @RequestBody Users user) {
        Users updatedUser = userService.updateUserName(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    //Atualiza senha Usuario
    @PutMapping("/password/{id}")
    public ResponseEntity<Users> updateUserPassword(@PathVariable long id, @RequestBody Users user) {
        Users updatedUser = userService.updateUserPassword(id, user);
        return ResponseEntity.ok(updatedUser);
    }


    // Endpoints para criar, atualizar, deletar usuários, login, logout, etc.
}