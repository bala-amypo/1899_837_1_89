package com.example.demo.model;

public class User {

    private Long id;
    private String email;
    private String password;
    private String role;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {     // ✅ REQUIRED
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) { // ✅ REQUIRED
        this.role = role;
    }
}
