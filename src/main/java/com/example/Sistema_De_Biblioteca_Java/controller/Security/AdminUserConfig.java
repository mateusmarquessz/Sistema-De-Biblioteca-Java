package com.example.Sistema_De_Biblioteca_Java.controller.Security;

import com.example.Sistema_De_Biblioteca_Java.entity.Role;
import com.example.Sistema_De_Biblioteca_Java.entity.Users;
import com.example.Sistema_De_Biblioteca_Java.repository.RoleRepository;
import com.example.Sistema_De_Biblioteca_Java.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        if (roleAdmin == null) {
            System.err.println("Admin role not found, unable to create admin user.");
            return; // Exit early if roleAdmin is not found
        }

        var userAdmin = userRepository.findByName("admin");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("admin already exists"),
                () -> {
                    var user = new Users();
                    user.setName("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin)); // This will not cause NullPointerException
                    userRepository.save(user);
                }
        );
    }
}
