package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl {
    private final InvoiceRepository invoiceRepo;
    private final UserRepository userRepo;
    private final VendorRepository vendorRepo;
    private final CategorizationRuleRepository ruleRepo;
    private final InvoiceCategorizationEngine engine;

    [cite_start]// Constructor Injection is MANDATORY [cite: 58, 750]
    public InvoiceServiceImpl(InvoiceRepository invoiceRepo, UserRepository userRepo, 
                              VendorRepository vendorRepo, CategorizationRuleRepository ruleRepo, 
                              InvoiceCategorizationEngine engine) {
        this.invoiceRepo = invoiceRepo;
        this.userRepo = userRepo;
        this.vendorRepo = vendorRepo;
        this.ruleRepo = ruleRepo;
        this.engine = engine;
    }

    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found")); [cite_start]// [cite: 365, 559]
        Vendor vendor = vendorRepo.findById(vendorId).orElseThrow(() -> new ResourceNotFoundException("Vendor not found")); [cite_start]// [cite: 367]
        
        invoice.setUploadedBy(user); [cite_start]// [cite: 370]
        invoice.setVendor(vendor); [cite_start]// [cite: 371]
        invoice.setCategory(null); [cite_start]// [cite: 373]
        return invoiceRepo.save(invoice);
    }
}