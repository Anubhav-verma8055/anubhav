package com.example.InvoiceApplication.Service;

import com.example.InvoiceApplication.DTO.BillDTO;
import com.example.InvoiceApplication.Entity.Bill;
import com.example.InvoiceApplication.Entity.Item;
import com.example.InvoiceApplication.Repository.BillRepository;
import com.example.InvoiceApplication.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ItemRepository itemRepository;

    //  Get All Bills
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    //  Update an Existing Bill
    public Bill updateBill(Long billId, Bill updatedBill) {
        Optional<Bill> existingBillOpt = billRepository.findById(billId);
        if (existingBillOpt.isPresent()) {
            Bill existingBill = existingBillOpt.get();
            // Update fields (for example)
            existingBill.setCustomerId(updatedBill.getCustomerId());
            existingBill.setTimestamp(updatedBill.getTimestamp());
            existingBill.setTotalAmount(updatedBill.getTotalAmount());
            // Save updated bill
            return billRepository.save(existingBill);
        } else {
            throw new RuntimeException("Bill not found with ID: " + billId);
        }
    }

    //  Create a New Bill
    public Bill createBill(Bill bill) {
        // You can add additional validation or business logic here before saving
            bill.setTimestamp(LocalDateTime.now());  // Set current date if timestamp is not passed
        return billRepository.save(bill);

    }

    //  Delete a Bill by Bill ID
    public void deleteBill(Long billId) {
        Optional<Bill> billOptional = billRepository.findById(billId);
        if (billOptional.isPresent()) {
            billRepository.delete(billOptional.get());
        } else {
            throw new RuntimeException("Bill not found with ID: " + billId);
        }
    }

    // Method to get Bill along with its associated items
    public BillDTO getBillWithItems(Long billId) {
        // Fetch Bill by ID
        Optional<Bill> billOptional = billRepository.findById(billId);
        if (billOptional.isPresent()) {
            Bill bill = billOptional.get();
            // Fetch associated items for the bill
            List<Item> items = itemRepository.findByBillId(bill.getId());

            // Create and return BillDTO with items
            return mapBillToDTO(bill, items);
        } else {
            throw new RuntimeException("Bill not found with ID: " + billId);
        }
    }

    // Method to get all Bills for a given customer
    public List<BillDTO> getBillsByCustomerId(Long customerId) {
        // Fetch all bills by customer ID
        List<Bill> bills = billRepository.findByCustomerId(customerId);
        if (!bills.isEmpty()) {
            // For each bill, fetch associated items and map to DTO
            return bills.stream()
                    .map(bill -> {
                        List<Item> items = itemRepository.findByBillId(bill.getId());
                        return mapBillToDTO(bill, items);
                    })
                    .collect(Collectors.toList());
        }
        else {
            throw new RuntimeException("No bills found for Customer ID: " + customerId);
        }
    }

    // Helper method to map Bill and Items to BillDTO
    private BillDTO mapBillToDTO(Bill bill, List<Item> items) {
        BillDTO billDTO = new BillDTO();
        billDTO.setBillId(bill.getId());
        billDTO.setCustomerId(bill.getCustomerId());
        billDTO.setTimestamp(bill.getTimestamp());
        billDTO.setTotalAmount(bill.getTotalAmount());
        billDTO.setItems(items);  // Associate items with the bill
        return billDTO;
    }

}
