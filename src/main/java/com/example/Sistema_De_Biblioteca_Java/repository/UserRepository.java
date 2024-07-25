package com.example.Sistema_De_Biblioteca_Java.repository;

import com.example.Sistema_De_Biblioteca_Java.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByName(String Name);
}