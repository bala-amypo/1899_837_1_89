package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {


    // Must be at least 32 characters
    private static final String JWT_SECRET =
            "my_jwt_secret_key_12345678901234567890";

    private static final long JWT_EXPIRATION_MS = 86400000; // 24 hours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }

    /**
     * Create JWT token
     * Claims: userId, email, role
     */
    public String createToken(Long userId, String email, String role) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("role", role);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validate JWT token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Extract email (subject)
     */
    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Extract userId
     */
    public Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    /**
     * Extract role
     */
    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
