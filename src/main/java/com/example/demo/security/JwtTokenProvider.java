package com.example.demo.security;

import com.example.demo.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private String jwtSecret = "your_very_strong_random_secret_key_minimum_32_chars"; // [cite: 99]
    private long jwtExpirationMs = 86400000; // 24 hours [cite: 100, 762]

    public String createToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId()); // [cite: 544, 901]
        claims.put("role", user.getRole()); // [cite: 544, 901]
        claims.put("email", user.getEmail()); // [cite: 544, 901]

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret) // [cite: 545, 763]
                .compact();
    }
    
    // Add validateToken and extraction methods as required by SRS [cite: 547-551]
}