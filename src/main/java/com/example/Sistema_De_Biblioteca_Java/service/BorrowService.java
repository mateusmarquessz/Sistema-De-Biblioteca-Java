package com.example.Sistema_De_Biblioteca_Java.service;

import Exceptions.BookNotFoundException;
import com.example.Sistema_De_Biblioteca_Java.entity.Book;
import com.example.Sistema_De_Biblioteca_Java.entity.Borrow;
import com.example.Sistema_De_Biblioteca_Java.entity.Users;
import com.example.Sistema_De_Biblioteca_Java.repository.BookRepository;
import com.example.Sistema_De_Biblioteca_Java.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.example.Sistema_De_Biblioteca_Java.repository.BorrowRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

   @Transactional
   public void borrowBook(Long bookId, Long userId) {
       Book book = findBookById(bookId);
       Users user = findUserById(userId);

       checkBookAvailability(book);

       markBookAsUnavailable(book);
       createAndSaveBorrow(book, user);
   }

    private Book findBookById(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            throw new RuntimeException("Book not found");
        }
        return optionalBook.get();
    }

    private Users findUserById(Long userId) {
        Optional<Users> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return optionalUser.get();
    }

    private void checkBookAvailability(Book book) {
        if (!book.checkAvailability()) {
            throw new RuntimeException("Book is already borrowed");
        }
    }

    private void markBookAsUnavailable(Book book) {
        book.setAvailable(false);
        bookRepository.save(book);
    }

    private void createAndSaveBorrow(Book book, Users user) {
        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setUser(user);
        borrow.setBorrowAt(LocalDateTime.now());
        borrow.setUntilAt(LocalDateTime.now().plusDays(14));
        borrowRepository.save(borrow);
    }

    @Transactional
    public void returnBook(Long borrowId) {
        Optional<Borrow> optionalBorrow = borrowRepository.findById(borrowId);
        if (!optionalBorrow.isPresent()) {
            throw new RuntimeException("Borrow record not found");
        }

        Borrow borrow = optionalBorrow.get();
        Book book = borrow.getBook();

        book.setAvailable(true);
        bookRepository.save(book);

        borrowRepository.delete(borrow);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Optional<Borrow> getBorrowById(Long id) {
        return borrowRepository.findById(id);
    }
}