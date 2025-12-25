package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Required for Tests 53 and 31 [cite: 530, 876]
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs [cite: 809]
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless JWT [cite: 532]
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/hello-servlet", "/swagger-ui/**").permitAll() // Public endpoints [cite: 533, 633]
                .requestMatchers("/api/vendors/**", "/api/categories/**").hasRole("ADMIN") // Role-based access [cite: 459, 475, 535]
                .anyRequest().authenticated()); // Protect all other /api/** endpoints [cite: 534]
        
        return http.build();
    }
}