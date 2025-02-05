package com.example.InvoiceApplication.Repository;

import com.example.InvoiceApplication.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
