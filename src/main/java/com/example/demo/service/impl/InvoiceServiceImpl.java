package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.util.InvoiceCategorizationEngine;
import com.example.demo.exception.ResourceNotFoundException;
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
    private final InvoiceCategorizationEngine engine;

    // Constructor Injection (Test 20, 24)
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, 
                              UserRepository userRepository, 
                              VendorRepository vendorRepository,
                              CategorizationRuleRepository ruleRepository,
                              InvoiceCategorizationEngine engine) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
        this.engine = engine;
    }

    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        // Test 13: User not found
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        invoice.setUploadedBy(user);
        invoice.setVendor(vendor);

        // Run Categorization Engine
        List<CategorizationRule> rules = ruleRepository.findAll();
        Category assignedCategory = engine.determineCategory(invoice, rules);
        invoice.setCategory(assignedCategory);

        return invoiceRepository.save(invoice);
    }

    public Invoice getInvoice(Long id) {
        // Test 15: Invoice not found
        return invoiceRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
    }

    public List<Invoice> getInvoicesByUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return invoiceRepository.findByUploadedBy(user);
    }
}