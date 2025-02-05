package com.example.InvoiceApplication.Controller;


import com.example.InvoiceApplication.DTO.BillDTO;
import com.example.InvoiceApplication.Entity.Bill;
import com.example.InvoiceApplication.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;
    // Get Bill by ID along with associated items
    @GetMapping("/getBill/{billId}")
    public BillDTO getBillById(@PathVariable Long billId) {
        try {
            return billService.getBillWithItems(billId);
        } catch (Exception e) {
            throw new RuntimeException("Bill not found for the given Bill ID");
        }
    }


    @GetMapping("/getAllBills")
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    // 4. Create a New Bill
    @PostMapping("/createBill")
    public Bill createBill(@RequestBody Bill bill) {
        return billService.createBill(bill);
    }

    // 5. Update an Existing Bill
    @PutMapping("/updateBill/{billId}")
    public Bill updateBill(@PathVariable Long billId, @RequestBody Bill bill) {
        return billService.updateBill(billId, bill);
    }

    // 6. Delete a Bill by Bill ID
    @DeleteMapping("/deleteBill/{billId}")
    public String deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return "Bill with ID " + billId + " has been deleted successfully!";
    }


    // Get all Bills for a customer
    @GetMapping("/getBillsByCustomer/{customerId}")
    public List<BillDTO> getBillsByCustomer(@PathVariable Long customerId) {
        try {
            return billService.getBillsByCustomerId(customerId);
        } catch (Exception e) {
            throw new RuntimeException("Bills not found for the given Customer ID");
        }
    }
}
