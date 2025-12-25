package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"vendor_id", "invoiceNumber"})
})
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private Double amount;
    private String description;
    private LocalDateTime uploadedAt;

    @ManyToOne @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @ManyToOne @JoinColumn(name = "uploaded_by_id", nullable = false)
    private User uploadedBy;

    @ManyToOne @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }
    // Getters and Setters...
}