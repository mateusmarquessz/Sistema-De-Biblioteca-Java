package com.example.Sistema_De_Biblioteca_Java.controller;

import com.example.Sistema_De_Biblioteca_Java.entity.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Sistema_De_Biblioteca_Java.service.BorrowService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;


    @PostMapping
    public ResponseEntity<Borrow> createBorrow(@RequestBody Borrow borrow) {
        Borrow savedBorrow = borrowService.saveBorrow(borrow);
        return ResponseEntity.ok(savedBorrow);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id) {
        Optional<Borrow> borrow = borrowService.getBorrowById(id);
        return borrow.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Borrow>> getAllBorrows() {
        List<Borrow> borrows = borrowService.getAllBorrows();
        return ResponseEntity.ok(borrows);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Borrow> updateBorrow(@PathVariable Long id, @RequestBody Borrow borrowDetails) {
        Borrow updatedBorrow = borrowService.updateBorrow(id, borrowDetails);
        return ResponseEntity.ok(updatedBorrow);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable Long id) {
        borrowService.deleteBorrow(id);
        return ResponseEntity.noContent().build();
    }
// Endpoints para emprestar e devolver livros
}