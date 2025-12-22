package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vendors", uniqueConstraints = @UniqueConstraint(name = "uk_vendor_name", columnNames = "vendorName"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vendorName;

    private String contactEmail;
    private String address;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToMany(mappedBy = "favoriteVendors")
    @Builder.Default
    private Set<User> users = new HashSet<>();

    @PrePersist
    protected void prePersist() {
        this.createdAt = Instant.now();
    }
}
