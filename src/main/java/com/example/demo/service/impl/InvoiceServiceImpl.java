package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class InvoiceServiceImpl {
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;
    private final CategorizationRuleRepository ruleRepository;
    private final InvoiceCategorizationEngine categorizationEngine;

    // Strict constructor injection required for TestNG mocks [cite: 361, 750, 830]
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, UserRepository userRepository,
                              VendorRepository vendorRepository, CategorizationRuleRepository ruleRepository,
                              InvoiceCategorizationEngine categorizationEngine) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
        this.categorizationEngine = categorizationEngine;
    }

    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found")); // [cite: 365, 852]
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new ResourceNotFoundException("Vendor not found")); // [cite: 367]

        invoice.setUploadedBy(user); // [cite: 370]
        invoice.setVendor(vendor); // [cite: 371]
        invoice.setCategory(null); // Category must be null initially [cite: 373, 849]
        
        return invoiceRepository.save(invoice); // [cite: 374]
    }

    public Invoice categorizeInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new ResourceNotFoundException("Invoice not found")); // [cite: 377, 856]

        List<CategorizationRule> rules = ruleRepository.findAll(); // [cite: 378]
        Category category = categorizationEngine.determineCategory(invoice, rules); // [cite: 379]
        
        invoice.setCategory(category); // [cite: 380]
        return invoiceRepository.save(invoice); // [cite: 381]
    }
}