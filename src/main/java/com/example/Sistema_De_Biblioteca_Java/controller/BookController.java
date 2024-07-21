package com.example.Sistema_De_Biblioteca_Java.controller;

import com.example.Sistema_De_Biblioteca_Java.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Sistema_De_Biblioteca_Java.service.BookService;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    //Pega todos os livros
    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    //Pega livro pelo id
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    //Cria Livro
    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createBook = bookService.createBook(book);
        return ResponseEntity.ok(createBook);
    }

    //Deletando livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable int id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    //Atualizando Livro
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        Book updateBook = bookService.updateBook(book, id);
        return ResponseEntity.ok(updateBook);
    }
    // Endpoints para criar, atualizar, deletar, listar e encontrar livros
}