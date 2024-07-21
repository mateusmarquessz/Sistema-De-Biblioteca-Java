package com.example.Sistema_De_Biblioteca_Java.service;

import Exceptions.BookNotFoundException;
import Exceptions.ResourceNotFoundException;
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

    //Livros pelo ID
    public Book getBookById(long id) {
        return bookRepository.findById(id).
                orElseThrow(() -> new BookNotFoundException("Book not found with id: "+ id));
    }

    //Cria Livro no Banco
    public Book createBook(Book book) {
        book.setCreatedAt(LocalDateTime.now());
        return bookRepository.save(book);
    }

    //Deleta Livro do Banco
    @Transactional
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    //Atualiza Book
    public Book updateBook(long id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        existingBook.setName(book.getName());
        existingBook.setCategory(book.getCategory());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setQtdPages(book.getQtdPages());
        existingBook.setEdition(book.getEdition());
        existingBook.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(existingBook);
    }

    // Métodos para criar, atualizar, deletar, listar e encontrar livros
}
