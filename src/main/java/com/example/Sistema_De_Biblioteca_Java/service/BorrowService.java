package com.example.Sistema_De_Biblioteca_Java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Sistema_De_Biblioteca_Java.repository.BorrowRepository;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    // Métodos para emprestar e devolver livros
}