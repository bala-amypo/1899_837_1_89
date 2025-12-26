package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategorizationRuleServiceImpl {
    private final CategorizationRuleRepository ruleRepository;
    private final CategoryRepository categoryRepository;

    public CategorizationRuleServiceImpl(CategorizationRuleRepository ruleRepository, CategoryRepository categoryRepository) {
        this.ruleRepository = ruleRepository;
        this.categoryRepository = categoryRepository;
    }

    public CategorizationRule createRule(Long categoryId, CategorizationRule rule) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        rule.setCategory(category);
        return ruleRepository.save(rule);
    }

    // Since repository doesn't have findByCategory, we filter via findAll or add method.
    // Given the simplicity, we stream findAll or you can add findByCategory to repo.
    public List<CategorizationRule> getRulesByCategory(Long categoryId) {
        return ruleRepository.findAll().stream()
                .filter(r -> r.getCategory().getId().equals(categoryId))
                .toList();
    }

    public void deleteRule(Long ruleId) {
        if (!ruleRepository.existsById(ruleId)) {
            throw new ResourceNotFoundException("Rule not found");
        }
        ruleRepository.deleteById(ruleId);
    }
}