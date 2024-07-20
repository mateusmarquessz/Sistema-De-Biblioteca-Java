package com.example.Sistema_De_Biblioteca_Java.service;

import com.example.Sistema_De_Biblioteca_Java.entity.Book;
import com.example.Sistema_De_Biblioteca_Java.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Sistema_De_Biblioteca_Java.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookService(UserRepository userRepository) {
        this.bookRepository = bookRepository;
    }

    //Lista todos os Livros
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Métodos para criar, atualizar, deletar, listar e encontrar livros
}
