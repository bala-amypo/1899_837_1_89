package com.example.demo.util;

import com.example.demo.entity.Category;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.CategorizationRule;

import java.util.List;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {

    public Category determineCategory(
            Invoice invoice,
            List<CategorizationRule> rules) {

        if (rules == null || rules.isEmpty()) {
            return null;
        }

        return rules.stream()
                .sorted((a, b) -> b.getPriority() - a.getPriority())
                .filter(rule -> matches(invoice.getDescription(), rule))
                .map(CategorizationRule::getCategory)
                .findFirst()
                .orElse(null);
    }

    private boolean matches(String description, CategorizationRule rule) {
        if (description == null || rule == null) {
            return false;
        }

        return switch (rule.getMatchType()) {
            case "EXACT" ->
                    description.equalsIgnoreCase(rule.getKeyword());
            case "CONTAINS" ->
                    description.toLowerCase()
                            .contains(rule.getKeyword().toLowerCase());
            case "REGEX" ->
                    Pattern.compile(rule.getKeyword())
                            .matcher(description)
                            .find();
            default -> false;
        };
    }
}
