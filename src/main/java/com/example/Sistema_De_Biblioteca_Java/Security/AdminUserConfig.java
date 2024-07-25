package com.example.Sistema_De_Biblioteca_Java.Security;

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

        // Crie o papel ADMIN se não existir
        if (roleAdmin == null) {
            Role newRoleAdmin = new Role();
            newRoleAdmin.setName(Role.Values.ADMIN.name());
            roleAdmin = roleRepository.save(newRoleAdmin);
            System.out.println("Admin role created.");
        }

        // Torne roleAdmin efetivamente final para uso na expressão lambda
        final Role finalRoleAdmin = roleAdmin;

        var userAdmin = userRepository.findByName("admin");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("admin already exists"),
                () -> {
                    var user = new Users();
                    user.setName("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(finalRoleAdmin));
                    userRepository.save(user);
                    System.out.println("admin user created.");
                }
        );
    }
}
