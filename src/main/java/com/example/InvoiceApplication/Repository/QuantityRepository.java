package com.example.InvoiceApplication.Repository;

import com.example.InvoiceApplication.Entity.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {

    List<Quantity> findQuantitiesByBillId(Long billId);
}
