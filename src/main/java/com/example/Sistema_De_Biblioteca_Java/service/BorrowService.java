package com.example.Sistema_De_Biblioteca_Java.service;

import com.example.Sistema_De_Biblioteca_Java.entity.Book;
import com.example.Sistema_De_Biblioteca_Java.entity.Borrow;
import com.example.Sistema_De_Biblioteca_Java.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Sistema_De_Biblioteca_Java.repository.BorrowRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    public Borrow saveBorrow(Borrow borrow) {
        Book book = borrow.getBook();
        if (book.getAvailable()) {
            book.setAvailable(false);
            bookRepository.save(book);

            borrow.setBorrowAt(LocalDateTime.now());
            return borrowRepository.save(borrow);
        } else {
            throw new RuntimeException("Book is not available for borrowing"); // Lança exceção se o livro não estiver disponível
        }
    }

    public Optional<Borrow> getBorrowById(Long id) {
        return borrowRepository.findById((long) id);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow updateBorrow(Long id, Borrow borrowDetails){
        Borrow borrow = borrowRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrow not found with id " + id));
        borrow.setUser(borrowDetails.getUser());
        borrow.setBook(borrowDetails.getBook());
        borrow.setBorrowAt(borrowDetails.getBorrowAt());
        borrow.setUntilAt(borrowDetails.getUntilAt());
        return borrowRepository.save(borrow);
    }

    public void deleteBorrow(Long id) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrow not found with id " + id));
        Book book = borrow.getBook();
        book.setAvailable(true);
        bookRepository.save(book);
        borrowRepository.delete(borrow);
    }

    // Métodos para emprestar e devolver livros
}