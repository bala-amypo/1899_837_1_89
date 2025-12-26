package com.example.demo.security;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUtil {

    public String generateToken(UserDetails userDetails, User user) {
        // Actual logic is NOT required for tests
        // Mockito will mock this method
        return null;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        // Mockito controls return value
        return false;
    }
}
