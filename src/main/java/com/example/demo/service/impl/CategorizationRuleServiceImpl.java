package com.example.demo.service.impl;

import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CategorizationRuleServiceImpl implements CategorizationRuleService {

    private final CategorizationRuleRepository ruleRepository;

    public CategorizationRuleServiceImpl(CategorizationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public CategorizationRule createRule(CategorizationRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public List<CategorizationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }
}