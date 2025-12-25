package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.model.Invoice;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        if (invoice.getDescription() == null || rules == null || rules.isEmpty()) {
            return null;
        }

        // Sort rules by priority (Descending)
        // Test requirement: Higher priority evaluated first
        return rules.stream()
                .sorted(Comparator.comparingInt(CategorizationRule::getPriority).reversed())
                .filter(rule -> matches(invoice.getDescription(), rule))
                .findFirst()
                .map(CategorizationRule::getCategory)
                .orElse(null);
    }

    private boolean matches(String description, CategorizationRule rule) {
        String input = description;
        String keyword = rule.getKeyword();

        switch (rule.getMatchType().toUpperCase()) {
            case "EXACT":
                return input.equalsIgnoreCase(keyword);
            case "CONTAINS":
                return input.toLowerCase().contains(keyword.toLowerCase());
            case "REGEX":
                try {
                    return Pattern.compile(keyword, Pattern.CASE_INSENSITIVE).matcher(input).find();
                } catch (Exception e) {
                    return false;
                }
            default:
                return false;
        }
    }
}