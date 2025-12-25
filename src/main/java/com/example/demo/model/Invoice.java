package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices", uniqueConstraints = {
    [cite_start]@UniqueConstraint(columnNames = {"vendor_id", "invoiceNumber"}) // [cite: 194, 768]
})
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite_start]// [cite: 169]
    private String invoiceNumber;
    private Double amount;
    private String description;
    private LocalDateTime uploadedAt;

    @ManyToOne @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor; [cite_start]// [cite: 171]

    @ManyToOne @JoinColumn(name = "uploaded_by_id", nullable = false)
    private User uploadedBy; [cite_start]// [cite: 187]

    @ManyToOne @JoinColumn(name = "category_id")
    private Category category; [cite_start]// [cite: 184]

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now(); [cite_start]// [cite: 190]
    }
    // Getters and Setters
}