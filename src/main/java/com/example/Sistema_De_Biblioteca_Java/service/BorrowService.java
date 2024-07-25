package com.example.Sistema_De_Biblioteca_Java.service;

import com.example.Sistema_De_Biblioteca_Java.entity.Borrow;
import com.example.Sistema_De_Biblioteca_Java.entity.Book;
import com.example.Sistema_De_Biblioteca_Java.entity.Users;
import com.example.Sistema_De_Biblioteca_Java.repository.BorrowRepository;
import com.example.Sistema_De_Biblioteca_Java.repository.BookRepository;
import com.example.Sistema_De_Biblioteca_Java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Cria e salva um novo empréstimo
    public void createAndSaveBorrow(Long bookId, Long userId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<Users> userOptional = userRepository.findById(userId);

        if (bookOptional.isEmpty()) {
            throw new RuntimeException("Book not found");
        }

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Book book = bookOptional.get();
        if (!book.checkAvailability()) {
            throw new RuntimeException("Book is not available for borrowing");
        }

        Users user = userOptional.get();
        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setUser(user);
        borrowRepository.save(borrow);

        // Opcional: Defina o livro como não disponível se quiser marcá-lo como emprestado
        book.setAvailable(false);
        bookRepository.save(book);
    }


    // Devolve um livro
    public void returnBook(Long borrowId) {
        Optional<Borrow> borrow = borrowRepository.findById(borrowId);

        if (borrow.isEmpty()) {
            throw new RuntimeException("Borrow record not found");
        }
        Book book = borrow.get().getBook();
        // Remove o registro de empréstimo
        book.setAvailable(true);
        bookRepository.save(book);
        borrowRepository.delete(borrow.get());
    }

    // Lista todos os empréstimos
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    // Obtém detalhes de um empréstimo específico
    public Optional<Borrow> getBorrowById(Long id) {
        return borrowRepository.findById(id);
    }
}
