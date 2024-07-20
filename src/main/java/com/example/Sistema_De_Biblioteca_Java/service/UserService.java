package com.example.Sistema_De_Biblioteca_Java.service;
import Exceptions.ResourceNotFoundException;
import Exceptions.UserNotFoundException;
import com.example.Sistema_De_Biblioteca_Java.entity.Users;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Sistema_De_Biblioteca_Java.repository.UserRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
        return userRepository.save(user);
    }

    //Deleta Usuario pelo ID
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    //Atualiza Usuario
    public Users updateUser(Long userId, Users user) {
        Users users = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        users.setName(user.getName());
        users.setPassword(user.getPassword());
        users.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(users);
    }

    // Métodos para criar, atualizar, deletar, login, logout, etc.
}
