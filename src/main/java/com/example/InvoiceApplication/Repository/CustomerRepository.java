package com.example.InvoiceApplication.Repository;

import com.example.InvoiceApplication.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    Optional<Customer> findByPhone(String phoneNumber);
//
//    Long findByPhone(String customerPhone);
}
