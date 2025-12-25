package com.example.demo.util;

import com.example.demo.model.*;
import java.util.List;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {
    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        if (rules == null || invoice.getDescription() == null) return null; [cite_start]// [cite: 603]

        return rules.stream()
            [cite_start].sorted((r1, r2) -> r2.getPriority().compareTo(r1.getPriority())) // [cite: 595, 676]
            .filter(rule -> {
                String desc = invoice.getDescription();
                String keyword = rule.getKeyword();
                return switch (rule.getMatchType().toUpperCase()) {
                    [cite_start]case "EXACT" -> desc.equalsIgnoreCase(keyword); // [cite: 599, 605]
                    case "CONTAINS" -> desc.toLowerCase().contains(keyword.toLowerCase()); [cite_start]// [cite: 600, 606]
                    case "REGEX" -> Pattern.compile(keyword).matcher(desc).find(); [cite_start]// [cite: 601, 607]
                    default -> false;
                };
            })
            .map(CategorizationRule::getCategory)
            .findFirst().orElse(null); [cite_start]// [cite: 678]
    }
}