package com.example.demo.service.impl;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VendorServiceImpl {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}