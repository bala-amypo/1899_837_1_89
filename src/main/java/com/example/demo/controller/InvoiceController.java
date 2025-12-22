package com.example.demo.controller;

import com.example.demo.model.Invoice;
import com.example.demo.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService) { this.invoiceService = invoiceService; }

    @PostMapping("/upload/{userId}/{vendorId}")
    public ResponseEntity<Invoice> upload(@PathVariable Long userId,
                                          @PathVariable Long vendorId,
                                          @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.uploadInvoice(userId, vendorId, invoice));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Invoice>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(invoiceService.getInvoicesByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getInvoice(id));
    }
}
