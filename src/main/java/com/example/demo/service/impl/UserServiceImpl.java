package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Must use constructor injection for TestNG mocks [cite: 299, 750]
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use"); // Required for Test 10 [cite: 303]
        }
        if (user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password too short"); // [cite: 304]
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // [cite: 305]
        return userRepository.save(user);
    }
}