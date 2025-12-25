package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Finds user by email; required by UserService
    Optional<User> findByEmail(String email);

    // Checks if an email exists; used for validation in UserService
    boolean existsByEmail(String email);
}