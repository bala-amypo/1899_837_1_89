package com.example.demo.service;

import com.example.demo.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
}