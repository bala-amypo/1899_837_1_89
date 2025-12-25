package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
[cite_start]@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")}) // [cite: 112, 131]
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite_start]// [cite: 105, 106]
    private String email;
    private String password;
    private String role; [cite_start]// [cite: 117]
    private LocalDateTime createdAt;

    @ManyToMany
    [cite_start]@JoinTable(name = "user_vendor_favorites") // [cite: 127]
    private Set<Vendor> favoriteVendors = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now(); [cite_start]// [cite: 122]
        if (this.role == null) this.role = "USER"; [cite_start]// [cite: 135]
    }
    // Getters and Setters
}