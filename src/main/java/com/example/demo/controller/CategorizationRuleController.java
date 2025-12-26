package com.example.demo.controller;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.impl.CategorizationRuleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class CategorizationRuleController {
    private final CategorizationRuleServiceImpl ruleService;

    public CategorizationRuleController(CategorizationRuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<CategorizationRule> createRule(@PathVariable Long categoryId, @RequestBody CategorizationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(categoryId, rule));
    }

    @GetMapping("/category/{categoryId}")
    public List<CategorizationRule> getRulesByCategory(@PathVariable Long categoryId) {
        return ruleService.getRulesByCategory(categoryId);
    }

    @DeleteMapping("/{ruleId}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long ruleId) {
        ruleService.deleteRule(ruleId);
        return ResponseEntity.ok().build();
    }
}