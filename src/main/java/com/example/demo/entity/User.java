// package com.example.demo.model;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;

// import java.time.LocalDateTime;
// import java.util.HashSet;
// import java.util.Set;

// @Entity
// @Table(
//     name = "users",
//     uniqueConstraints = @UniqueConstraint(columnNames = "email")
// )
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotBlank
//     private String fullName;

//     @Email
//     @NotBlank
//     private String email;

//     @NotBlank
//     private String password;

//     private String role;

//     private LocalDateTime createdAt;

//     @ManyToMany
//     @JoinTable(
//         name = "user_vendor_favorites",
//         joinColumns = @JoinColumn(name = "user_id"),
//         inverseJoinColumns = @JoinColumn(name = "vendor_id")
//     )
//     private Set<Vendor> favoriteVendors = new HashSet<>();

//     @PrePersist
//     public void prePersist() {
//         this.createdAt = LocalDateTime.now();
//         if (this.role == null) {
//             this.role = "USER";
//         }
//     }

//     // getters and setters
//     public Long getId() { return id; }
//     public String getFullName() { return fullName; }
//     public String getEmail() { return email; }
//     public String getPassword() { return password; }
//     public String getRole() { return role; }
//     public LocalDateTime getCreatedAt() { return createdAt; }
//     public Set<Vendor> getFavoriteVendors() { return favoriteVendors; }

//     public void setId(Long id) { this.id = id; }
//     public void setFullName(String fullName) { this.fullName = fullName; }
//     public void setEmail(String email) { this.email = email; }
//     public void setPassword(String password) { this.password = password; }
//     public void setRole(String role) { this.role = role; }
// }
