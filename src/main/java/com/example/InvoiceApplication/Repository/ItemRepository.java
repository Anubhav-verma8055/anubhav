package com.example.InvoiceApplication.Repository;

import com.example.InvoiceApplication.Entity.Bill;
import com.example.InvoiceApplication.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findItemsByBillId(Long billId);

    List<Item> findByBillId(Long id);


   // List<Bill> getBillsByCustomerId(Long customerId);
}
