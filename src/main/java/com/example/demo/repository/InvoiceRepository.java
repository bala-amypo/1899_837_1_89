package com.example.demo.repository;

import com.example.demo.model.Invoice;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByUploadedBy(User user);

    @Query("select i from Invoice i where i.amount > ?1")
    List<Invoice> findByAmountGreaterThanHql(Double amount);
}
