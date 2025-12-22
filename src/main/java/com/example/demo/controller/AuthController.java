package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Simple health check endpoint
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("AuthController is alive (no login/security configured).");
    }

    // Optional: return static info about the app
    @GetMapping("/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("Smart Invoice Categorization API - AuthController stub");
    }
}
