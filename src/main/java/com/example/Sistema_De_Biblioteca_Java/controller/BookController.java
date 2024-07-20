package com.example.Sistema_De_Biblioteca_Java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Sistema_De_Biblioteca_Java.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // Endpoints para criar, atualizar, deletar, listar e encontrar livros
}