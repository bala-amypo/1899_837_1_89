package com.example.demo.service.impl;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository repo;
    public CategoryServiceImpl(CategoryRepository repo) { this.repo = repo; }
}