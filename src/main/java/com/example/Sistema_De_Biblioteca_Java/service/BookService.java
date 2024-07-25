package com.example.Sistema_De_Biblioteca_Java.service;

import Exceptions.BookNotFoundException;
import com.example.Sistema_De_Biblioteca_Java.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Sistema_De_Biblioteca_Java.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


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
        if(book.getAvailable() == null){
            book.setAvailable(true);
        }
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
        Optional<Book> existingBook = bookRepository.findById(id);
        if(existingBook.isPresent()) {
            Book updatedBook = existingBook.get();
            updatedBook.setName(book.getName());
            updatedBook.setCategory(book.getCategory());
            updatedBook.setIsbn(book.getIsbn());
            updatedBook.setQtdPages(book.getQtdPages());
            updatedBook.setEdition(book.getEdition());
            updatedBook.setUpdatedAt(LocalDateTime.now());
            return bookRepository.save(updatedBook);
        }else{
            throw new BookNotFoundException("Book not found with id: "+ id);
        }

    }

    // Métodos para criar, atualizar, deletar, listar e encontrar livros
}
