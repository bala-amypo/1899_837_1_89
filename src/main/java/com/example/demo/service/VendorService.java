package com.example.demo.service;

import com.example.demo.model.Vendor;
import java.util.List;
import java.util.Optional;

public interface VendorService {
    Vendor createVendor(Vendor vendor);
    List<Vendor> getAllVendors();
    Optional<Vendor> getVendorById(Long id);
    // Add other methods if required by controller
}