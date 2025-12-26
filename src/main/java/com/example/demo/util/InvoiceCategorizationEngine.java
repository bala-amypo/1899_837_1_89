package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.model.Invoice;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice,
                                      List<CategorizationRule> rules) {

        if (rules == null || rules.isEmpty()) {
            return null;
        }

        String description = invoice.getDescription();
        if (description == null) {
            return null;
        }

        return rules.stream()
                // highest priority first
                .sorted(Comparator.comparing(CategorizationRule::getPriority).reversed())
                // find first matching rule
                .filter(rule -> matches(rule, description))
                // return category
                .map(CategorizationRule::getCategory)
                .findFirst()
                .orElse(null);
    }

    private boolean matches(CategorizationRule rule, String description) {

        String keyword = rule.getKeyword();
        String matchType = rule.getMatchType();

        if (keyword == null || matchType == null) {
            return false;
        }

        return switch (matchType.toUpperCase()) {

            case "EXACT" ->
                    description.equalsIgnoreCase(keyword);

            case "CONTAINS" ->
                    description.toLowerCase().contains(keyword.toLowerCase());

            case "REGEX" ->
                    Pattern.compile(keyword, Pattern.CASE_INSENSITIVE)
                           .matcher(description)
                           .matches();

            default -> false;
        };
    }
}
