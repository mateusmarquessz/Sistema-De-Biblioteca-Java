package com.example.Sistema_De_Biblioteca_Java.repository;

import com.example.Sistema_De_Biblioteca_Java.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

}