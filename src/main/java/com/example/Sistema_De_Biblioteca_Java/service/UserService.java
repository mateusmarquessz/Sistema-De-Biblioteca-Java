package com.example.Sistema_De_Biblioteca_Java.service;
import com.example.Sistema_De_Biblioteca_Java.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Sistema_De_Biblioteca_Java.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    //Cria Usuario no Banco
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    //Deleta Usuario pelo ID
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    // Métodos para criar, atualizar, deletar, login, logout, etc.
}
