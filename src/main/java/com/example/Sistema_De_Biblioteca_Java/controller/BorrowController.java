package com.example.Sistema_De_Biblioteca_Java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Sistema_De_Biblioteca_Java.service.BorrowService;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

// Endpoints para emprestar e devolver livros
}