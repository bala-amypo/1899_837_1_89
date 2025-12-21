package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Entity
@Table(name = "categorization_rules")
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotBlank
    private String keyword;

    @NotBlank
    private String matchType; // EXACT, CONTAINS, REGEX

    @Positive
    private Integer priority;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // getters and setters
    public Long getId() { return id; }
    public Category getCategory() { return category; }
    public String getKeyword() { return keyword; }
    public String getMatchType() { return matchType; }
    public Integer getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setCategory(Category category) { this.category = category; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public void setMatchType(String matchType) { this.matchType = matchType; }
    public void setPriority(Integer priority) { this.priority = priority; }
}
