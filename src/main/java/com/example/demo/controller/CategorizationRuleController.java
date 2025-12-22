package com.example.demo.controller;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class CategorizationRuleController {

    private final CategorizationRuleService ruleService;
    public CategorizationRuleController(CategorizationRuleService ruleService) { this.ruleService = ruleService; }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<CategorizationRule> create(@PathVariable Long categoryId,
                                                     @RequestBody CategorizationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(categoryId, rule));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<CategorizationRule>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(ruleService.getRulesByCategory(categoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ruleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}
