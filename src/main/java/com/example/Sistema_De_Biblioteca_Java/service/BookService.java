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
    public Book updateBook(Book book, long id) {
        bookRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Book not found with id: "+ id));

        book.setName(book.getName());
        book.setCategory(book.getCategory());
        book.setIsbn(book.getIsbn());
        book.setQtdPages(book.getQtdPages());
        book.setEdition(book.getEdition());

        return bookRepository.save(book);
    }

    // Métodos para criar, atualizar, deletar, listar e encontrar livros
}
