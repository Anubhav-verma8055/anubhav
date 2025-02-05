package com.example.InvoiceApplication.Service;

import com.example.InvoiceApplication.DTO.InvoiceRequest;
import com.example.InvoiceApplication.Entity.Bill;
import com.example.InvoiceApplication.Entity.Customer;
import com.example.InvoiceApplication.Entity.Item;
import com.example.InvoiceApplication.Entity.Quantity;
import com.example.InvoiceApplication.Repository.BillRepository;
import com.example.InvoiceApplication.Repository.CustomerRepository;
import com.example.InvoiceApplication.Repository.ItemRepository;
import com.example.InvoiceApplication.Repository.QuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private QuantityRepository quantityRepository;

    @Autowired
    private BillRepository billRepository;

    // Fix the method to get items related to the bill
    private List<Item> getItemsForBill(Long billId) {
        return itemRepository.findItemsByBillId(billId); // Assumed repository method
    }

    // Fix the method to get quantities related to the bill
    private List<Quantity> getQuantitiesForBill(Long billId) {
        return quantityRepository.findQuantitiesByBillId(billId); // Assumed repository method
    }

    // Modify the method to handle all 3 cases (customerId, billId, timestamp)
    public InvoiceRequest.InvoiceDTO generateInvoice(Long customerId, Long billId, LocalDate timestamp, Long amountPaid) {
        Bill bill = null;

        // Handle customerId case
        if (customerId != null) {
            List<Bill> bills = billRepository.findByCustomerId(customerId);
            if (!bills.isEmpty()) {
                bill = bills.get(0); // Assuming first match
            }
        }
        // Handle billId case
        else if (billId != null) {
            Optional<Bill> billOptional = billRepository.findById(billId);
            if (billOptional.isPresent()) {
                bill = billOptional.get();
            }
        }
        // Handle timestamp case
        else if (timestamp != null) {
            List<Bill> bills = billRepository.findByTimestamp(timestamp);
            if (!bills.isEmpty()) {
                bill = bills.get(0);
            }
        }

        // If no bill found, throw error
        if (bill == null) {
            throw new RuntimeException("No invoice found for the given details.");
        }

        // Load the customer and associated items/quantities
        Customer customer = customerRepository.findById(bill.getCustomerId()).orElseThrow();
        List<Item> items = getItemsForBill(bill.getId()); // Fetch items related to the bill
        List<Quantity> quantities = getQuantitiesForBill(bill.getId()); // Fetch quantities related to the bill

        // Calculate the total amount for the invoice
        Long totalAmount = calculateTotalAmount(items, quantities, true); // Assuming interstate as true

        // Create and populate the InvoiceDTO
        InvoiceRequest.InvoiceDTO invoiceDTO = new InvoiceRequest.InvoiceDTO();
        invoiceDTO.setBillId(bill.getId());
        invoiceDTO.setCustomerName(customer.getCustomerName());
        invoiceDTO.setCustomerEmail(customer.getCustomerEmail());
        invoiceDTO.setItems(items);
        invoiceDTO.setQuantities(quantities);
        invoiceDTO.setTimestamp(bill.getTimestamp());
        invoiceDTO.setTotalAmount(totalAmount);
        invoiceDTO.setCompanyName("CryptoWorld Exchanges");
        invoiceDTO.setApprovedBy("Himanshu");

        return invoiceDTO;
    }

    private Long calculateTotalAmount(List<Item> items, List<Quantity> quantities, boolean isInterstate) {
        long totalAmount = 0;
        long totalCGST = 0;
        long totalSGST = 0;
        long totalIGST = 0;
        long totalCess = 0;

        // Loop through each item to calculate the total amount including taxes
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Quantity quantity = quantities.get(i);

            long itemTotalPrice = item.getItemPrice() * quantity.getAvailableQuantity();

            // Calculate CGST and SGST for intra-state (same state)
            if (!isInterstate) {  // Intra-state (CGST + SGST)
                long cgstAmount = (itemTotalPrice * item.getItemGst()) / 200;  // CGST is half of GST for intra-state
                long sgstAmount = cgstAmount;  // SGST is same as CGST for intra-state
                totalCGST += cgstAmount;
                totalSGST += sgstAmount;
                totalAmount += itemTotalPrice + cgstAmount + sgstAmount;
            }
            // Calculate IGST for inter-state (different states)
            else {  // Inter-state (IGST)
                long igstAmount = (itemTotalPrice * item.getItemGst()) / 100;  // IGST is full GST
                totalIGST += igstAmount;
                totalAmount += itemTotalPrice + igstAmount;
            }

            // Calculate Cess (if applicable)
            long cessAmount = (itemTotalPrice * item.getItemCess()) / 100;  // Assuming item.getItemCess() is the cess percentage
            totalCess += cessAmount;
            totalAmount += cessAmount;  // Add Cess to total amount
        }

        return totalAmount;
    }
}
