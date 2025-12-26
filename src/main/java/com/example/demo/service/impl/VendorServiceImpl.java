package com.example.demo.service.impl;
import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl {
    private final VendorRepository repo;
    public VendorServiceImpl(VendorRepository repo) { this.repo = repo; }
    public Vendor createVendor(Vendor vendor) { return repo.save(vendor); }
}