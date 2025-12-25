package com.example.demo.util;

import com.example.demo.model.Category;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InvoiceCategorizationEngine {
    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        if (rules == null || rules.isEmpty() || invoice.getDescription() == null) {
            return null; // [cite: 593, 603]
        }

        // Rules must be applied in descending priority order [cite: 245, 255, 595]
        List<CategorizationRule> sortedRules = rules.stream()
            .sorted((r1, r2) -> r2.getPriority().compareTo(r1.getPriority()))
            .collect(Collectors.toList());

        for (CategorizationRule rule : sortedRules) {
            String description = invoice.getDescription();
            String keyword = rule.getKeyword();

            boolean match = switch (rule.getMatchType().toUpperCase()) {
                case "EXACT" -> description.equalsIgnoreCase(keyword); // [cite: 239, 605]
                case "CONTAINS" -> description.toLowerCase().contains(keyword.toLowerCase()); // [cite: 240, 606]
                case "REGEX" -> Pattern.compile(keyword).matcher(description).find(); // [cite: 241, 607]
                default -> false;
            };

            if (match) {
                return rule.getCategory(); // [cite: 602]
            }
        }
        return null; // [cite: 603]
    }
}