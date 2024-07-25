package com.example.Sistema_De_Biblioteca_Java.repository;

import com.example.Sistema_De_Biblioteca_Java.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
