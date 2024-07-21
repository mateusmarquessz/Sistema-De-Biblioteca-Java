package com.example.Sistema_De_Biblioteca_Java.controller;

import com.example.Sistema_De_Biblioteca_Java.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Sistema_De_Biblioteca_Java.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    //Pega todos os livros
    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    //Pega livro pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        Optional<Book> book = Optional.ofNullable(bookService.getBookById(id));
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Cria Livro
    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createBook = bookService.createBook(book);
        return ResponseEntity.ok(createBook);
    }

    //Deletando livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    //Atualizando Livro
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }
    // Endpoints para criar, atualizar, deletar, listar e encontrar livros
}