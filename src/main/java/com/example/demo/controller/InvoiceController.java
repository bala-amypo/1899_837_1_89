package com.example.demo.controller;

import com.example.demo.model.Invoice;
import com.example.demo.service.impl.InvoiceServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@Tag(name = "Invoices Endpoints")
public class InvoiceController {
    private final InvoiceServiceImpl invoiceService;

    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/upload/{userId}/{vendorId}")
    public ResponseEntity<Invoice> uploadInvoice(
            @PathVariable Long userId, 
            @PathVariable Long vendorId, 
            @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.uploadInvoice(userId, vendorId, invoice)); //
    }

    @PostMapping("/categorize/{invoiceId}")
    public ResponseEntity<Invoice> categorizeInvoice(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(invoiceService.categorizeInvoice(invoiceId)); // Triggers Rule Engine
    }
}