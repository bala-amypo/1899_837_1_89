package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.model.Invoice;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class InvoiceCategorizationEngine {
    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        for (CategorizationRule rule : rules) {
            String desc = invoice.getDescription();
            if (desc == null) continue;
            
            boolean match = false;
            switch (rule.getMatchType()) {
                case "EXACT": match = desc.equals(rule.getKeyword()); break;
                case "CONTAINS": match = desc.toLowerCase().contains(rule.getKeyword().toLowerCase()); break;
                case "REGEX": match = Pattern.compile(rule.getKeyword()).matcher(desc).find(); break;
            }
            if (match) return rule.getCategory();
        }
        return null;
    }
}