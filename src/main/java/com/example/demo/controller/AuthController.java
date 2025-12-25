package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication") // Required for Swagger docs
public class AuthController {
    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;

    // Constructor Injection for tests
    public AuthController(UserServiceImpl userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        return ResponseEntity.ok(userService.registerUser(user)); //
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        // Validation logic for login
        User user = userService.findByEmail(request.getEmail()); //
        String token = jwtTokenProvider.createToken(user); // Returns JWT for Test 55
        return ResponseEntity.ok(token);
    }
}