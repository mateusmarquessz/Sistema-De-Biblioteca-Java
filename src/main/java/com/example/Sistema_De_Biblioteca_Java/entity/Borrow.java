package com.example.Sistema_De_Biblioteca_Java.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity

public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime borrowAt;
    private LocalDateTime untilAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getBorrowAt() {
        return borrowAt;
    }

    public void setBorrowAt(LocalDateTime borrowAt) {
        this.borrowAt = borrowAt;
    }

    public LocalDateTime getUntilAt() {
        return untilAt;
    }

    public void setUntilAt(LocalDateTime untilAt) {
        this.untilAt = untilAt;
    }
}