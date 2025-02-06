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

    /*private Long sequence = 0L;
    private LocalDate lastGeneratedDate = LocalDate.now();

    // Method to generate the bill ID
    private Long generateBillId() {
        LocalDate today = LocalDate.now();

        // Reset the sequence if the date has changed
        if (!today.equals(lastGeneratedDate)) {
            sequence = 0L; // Reset sequence
            lastGeneratedDate = today; // Update last generated date
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String datePart = today.format(formatter);

        // Increment the sequence for each new bill
        sequence++;

        // Combine date and sequence to form the bill ID
        return Long.parseLong(datePart + String.format("%03d", sequence)); //
    }
    * */

    // Fix the method to get items related to the bill
    private List<Item> getItemsForBill(Long billId) {
        return itemRepository.findItemsByBillId(billId); // Assumed repository method
    }

    // Fix the method to get quantities related to the bill
    private List<Quantity> getQuantitiesForBill(Long billId) {
        return quantityRepository.findQuantitiesByBillId(billId); // Assumed repository method
    }



    public InvoiceRequest.InvoiceDTO generateInvoice(Long customerId, Long billId, Long amountPaid) {

        // Step 1: Handle customerId case to find a matching bill
        Bill bill = null;

        if (customerId != null) {
            List<Bill> bills = billRepository.findByCustomerId(customerId);
            if (!bills.isEmpty()) {
                bill = bills.get(0); // Assuming first match
            }
        }
        // Step 1: Fetch the bill using the billId

        // If no bill found with customerId, try using billId
        if (bill == null && billId != null) {
            Optional<Bill> billOptional = billRepository.findById(billId);
            if (!billOptional.isPresent()) {
                throw new RuntimeException("No invoice found for the given billId: " + billId);
            }
            bill = billOptional.get();
        }

        // If still no bill found, throw an exception
        if (bill == null) {
            throw new RuntimeException("No invoice found for the given customerId or billId.");
        }

        bill.setAmountPaid(amountPaid);
        Customer customer = customerRepository.findById(bill.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found for the given customerId"));

        // Step 4: Fetch the items and quantities related to this bill
        List<Item> items = getItemsForBill(bill.getId());
        List<Quantity> quantities = getQuantitiesForBill(bill.getId());
        Boolean isInterState = bill.getIsInterState();  // Fetch the interstate flag from the bill

       bill.setAmountPaid(amountPaid);
       bill.setCustomerId(customer.getId());
        // Step 5: Calculate the total amount for the invoice
        Long totalAmount = calculateTotalAmount(items, quantities, isInterState, bill);

        // Step 6: Create the InvoiceDTO and populate it
        InvoiceRequest.InvoiceDTO invoiceDTO = new InvoiceRequest.InvoiceDTO();
        invoiceDTO.setAmountPaid(bill.getAmountPaid());
        invoiceDTO.setBillId(bill.getId());
        invoiceDTO.setCustomerName(customer.getCustomerName());
        invoiceDTO.setCustomerPhone(customer.getCustomerPhone());
        invoiceDTO.setCustomerEmail(customer.getCustomerEmail());
        invoiceDTO.setCustomerId(customer.getId());
        invoiceDTO.setItems(items);
        invoiceDTO.setQuantities(quantities);
        invoiceDTO.setTimestamp(bill.getTimestamp());
        invoiceDTO.setTotalAmount(totalAmount);
        invoiceDTO.setCompanyName("CryptoWorld Exchanges");
        invoiceDTO.setApprovedBy("Himanshu");

        return invoiceDTO;
    }


    private Long calculateTotalAmount(List<Item> items, List<Quantity> quantities, boolean isInterstate,Bill bill) {
        long totalAmount = 0;
        long totalCGST = 0;
        long totalSGST = 0;
        long totalIGST = 0;
        long totalCess = 0;

        // Loop through each item to calculate the total amount including taxes
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Quantity quantity = quantities.get(i);

            // Ensure requested quantity does not exceed available stock
            if (quantity.getRequestedQuantity() > quantity.getAvailableQuantity()) {
                throw new RuntimeException("Requested quantity exceeds available stock for item: " + item.getItemName());
            }

            long itemTotalPrice = item.getItemPrice() * quantity.getRequestedQuantity();

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


        // Update bill entity
      //  bill.setAmountPaid(amountPaid);
        bill.setTotalCGST(totalCGST);
        bill.setTotalSGST(totalSGST);
        bill.setTotalIGST(totalIGST);
        bill.setTotalCess(totalCess);
        bill.setTotalAmount(totalAmount);
        billRepository.save(bill);

        return totalAmount;
    }


}
