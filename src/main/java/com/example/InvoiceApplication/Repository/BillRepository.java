package com.example.InvoiceApplication.Repository;

import com.example.InvoiceApplication.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByCustomerId(Long customerId);
    List<Bill> findByTimestamp(LocalDate timestamp);
   // Optional<Bill> findById(Long  billId);

    Boolean findIsInterStateById(Long Id);

    Optional<Bill> findById(Long id);
}

