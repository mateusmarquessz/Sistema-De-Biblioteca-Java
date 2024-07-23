package com.example.Sistema_De_Biblioteca_Java.controller;

import com.example.Sistema_De_Biblioteca_Java.entity.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Sistema_De_Biblioteca_Java.service.BorrowService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    // Endpoint para emprestar um livro
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam Long bookId, @RequestParam Long userId) {
        try {
            borrowService.borrowBook(bookId, userId);
            return ResponseEntity.ok("Book borrowed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para devolver um livro
    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody Map<String, Long> requestBody) {
        Long borrowId = requestBody.get("borrowId");
        if (borrowId == null) {
            return ResponseEntity.badRequest().body("Missing 'borrowId' parameter");
        }
        try {
            borrowService.returnBook(borrowId);
            return ResponseEntity.ok("Book returned successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para listar todos os empréstimos
    @GetMapping
    public ResponseEntity<List<Borrow>> getAllBorrows() {
        List<Borrow> borrows = borrowService.getAllBorrows();
        return ResponseEntity.ok(borrows);
    }

    // Endpoint para obter detalhes de um empréstimo específico
    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id) {
        Optional<Borrow> borrow = borrowService.getBorrowById(id);
        return borrow.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

// Endpoints para emprestar e devolver livros
}