package com.example.demo.service.impl;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategorizationRuleServiceImpl {
    private final CategorizationRuleRepository ruleRepo;
    private final CategoryRepository catRepo;
    public CategorizationRuleServiceImpl(CategorizationRuleRepository r, CategoryRepository c) {
        this.ruleRepo = r; this.catRepo = c;
    }
}