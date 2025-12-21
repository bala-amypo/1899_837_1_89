package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // simple inner request class
    static class AuthRequest {
        public String email;
        public String password;
    }

    // simple inner response class
    static class AuthResponse {
        public String token;
        public AuthResponse(String token) {
            this.token = token;
        }
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return new AuthResponse("dummy-token");
    }
}
