package com.example.Sistema_De_Biblioteca_Java.service;
import Exceptions.ResourceNotFoundException;
import Exceptions.UserNotFoundException;
import com.example.Sistema_De_Biblioteca_Java.entity.Users;
import com.example.Sistema_De_Biblioteca_Java.repository.BorrowRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Sistema_De_Biblioteca_Java.repository.UserRepository;


import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Lista todos os Usuarios
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    //Usuario pelo ID
    public Users findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    //Cria Usuario no Banco
    public Users createUser(Users user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //Deleta Usuario pelo ID
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    //Atualiza Nome do Usuario
    public Users updateUserName(Long userId, Users user) {
        Users users = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        users.setName(user.getName());
        return userRepository.save(users);
    }

    //Atualiza Senha do Usuario
    public Users updateUserPassword(Long userId, Users user){
        Users users = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        users.setPassword(user.getPassword());
        return userRepository.save(users);
    }

    // Métodos para criar, atualizar, deletar, login, logout, etc.
}
