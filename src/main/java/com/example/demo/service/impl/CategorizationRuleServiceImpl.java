package com.example.demo.service.impl;

import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.repository.CategoryRepository; // Add this import
import com.example.demo.service.CategorizationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorizationRuleServiceImpl implements CategorizationRuleService {

    private final CategorizationRuleRepository repository;
    private final CategoryRepository categoryRepository; // Add this field

    // Update Constructor to match what the Test expects (2 arguments)
    @Autowired
    public CategorizationRuleServiceImpl(CategorizationRuleRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategorizationRule createRule(CategorizationRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<CategorizationRule> getAllRules() {
        return repository.findAll();
    }
    
    // Add other methods as required by interface...
}