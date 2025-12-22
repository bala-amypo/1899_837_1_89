package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CategorizationRuleRepository ruleRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              CategorizationRuleRepository ruleRepository) {
        this.invoiceRepository = invoiceRepository;
        this.ruleRepository = ruleRepository;
    }

    // ✅ FIXED METHOD
    @Override
    public Invoice getInvoice(Long invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invoice not found"));
    }

    @Override
    public Invoice categorizeInvoice(Long invoiceId) {

        Invoice invoice = getInvoice(invoiceId);

        String description = invoice.getDescription();
        List<CategorizationRule> rules =
                ruleRepository.findMatchingRulesByDescription(description);

        rules.sort(Comparator.comparing(CategorizationRule::getPriority).reversed());

        Category matchedCategory = null;

        for (CategorizationRule rule : rules) {

            String keyword = rule.getKeyword();
            String matchType = rule.getMatchType();

            if ("EXACT".equals(matchType)) {
                if (description.equalsIgnoreCase(keyword)) {
                    matchedCategory = rule.getCategory();
                    break;
                }
            } else if ("CONTAINS".equals(matchType)) {
                if (description.toLowerCase()
                        .contains(keyword.toLowerCase())) {
                    matchedCategory = rule.getCategory();
                    break;
                }
            } else if ("REGEX".equals(matchType)) {
                if (Pattern.compile(keyword, Pattern.CASE_INSENSITIVE)
                        .matcher(description).find()) {
                    matchedCategory = rule.getCategory();
                    break;
                }
            }
        }

        invoice.setCategory(matchedCategory);
        return invoiceRepository.save(invoice);
    }
}
