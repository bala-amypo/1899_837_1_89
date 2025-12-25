public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    Vendor vendor = vendorRepository.findById(vendorId)
        .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

    invoice.setUploadedBy(user);
    invoice.setVendor(vendor);
    invoice.setCategory(null);

    return invoiceRepository.save(invoice);
}
