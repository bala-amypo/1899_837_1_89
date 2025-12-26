package com.example.demo.service.impl;

import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.repository.CategoryRepository;

public class CategorizationRuleServiceImpl {

    private final CategorizationRuleRepository ruleRepository;
    private final CategoryRepository categoryRepository;

    public CategorizationRuleServiceImpl(
            CategorizationRuleRepository ruleRepository,
            CategoryRepository categoryRepository) {
        this.ruleRepository = ruleRepository;
        this.categoryRepository = categoryRepository;
    }
}
