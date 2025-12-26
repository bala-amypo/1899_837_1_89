package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Invoice;
import com.example.demo.model.User;
import com.example.demo.model.Vendor;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvoiceServiceImpl {
    private final InvoiceRepository invoiceRepo;
    private final UserRepository userRepo;
    private final VendorRepository vendorRepo;
    private final CategorizationRuleRepository ruleRepo;
    private final InvoiceCategorizationEngine engine;

    public InvoiceServiceImpl(InvoiceRepository i, UserRepository u, VendorRepository v, CategorizationRuleRepository r, InvoiceCategorizationEngine e) {
        this.invoiceRepo = i; this.userRepo = u; this.vendorRepo = v; this.ruleRepo = r; this.engine = e;
    }

    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Vendor vendor = vendorRepo.findById(vendorId).orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        
        invoice.setUploadedBy(user);
        invoice.setVendor(vendor);
        // Category explicitly null per test requirement
        invoice.setCategory(null);
        return invoiceRepo.save(invoice);
    }

    public Invoice getInvoice(Long id) {
        return invoiceRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
    }

    public List<Invoice> getInvoicesByUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return invoiceRepo.findByUploadedBy(user);
    }
}