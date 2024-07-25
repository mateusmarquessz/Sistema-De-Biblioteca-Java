package com.example.Sistema_De_Biblioteca_Java.controller;

import com.example.Sistema_De_Biblioteca_Java.entity.Role;
import com.example.Sistema_De_Biblioteca_Java.entity.Users;
import com.example.Sistema_De_Biblioteca_Java.repository.RoleRepository;
import com.example.Sistema_De_Biblioteca_Java.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.Sistema_De_Biblioteca_Java.service.UserService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private UserService service;
    private RoleRepository roleRepository;

    public UserController(UserService service,
                          UserService userService,
                          RoleRepository roleRepository,
                          UserRepository userRepository) {
        this.service = service;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    //Pega Todos os Usuarios
    @GetMapping()
    public ResponseEntity<List<Users>> getAllUsers() {
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    //Pega o Usuario pelo ID
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable long id) {
        return service.findById(id);
    }

    //Cria Usuario
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {

        var userFromDb = userRepository.findByName(user.getName());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already exists");
        }

        Users createUser =userService.createUser(user);
        return ResponseEntity.ok(createUser);

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