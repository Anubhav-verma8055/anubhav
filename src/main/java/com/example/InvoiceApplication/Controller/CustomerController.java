package com.example.InvoiceApplication.Controller;

import com.example.InvoiceApplication.Entity.Customer;
import com.example.InvoiceApplication.Repository.CustomerRepository;
import com.example.InvoiceApplication.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    //create a new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
      return customerService.getAllCustomers();

    }

    // Get a customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RuntimeException("Not found customer record");
        }
    }

        // Update an existing customer
        @PutMapping("/{id}")
        public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
            Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
            if (updatedCustomer != null) {
                return updatedCustomer;
            }
            else{
                throw new RuntimeException("Not found this record");// 404 if customer not found
            }
        }

    // Delete a customer by ID
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        boolean deleted = customerService.deleteCustomer(id);

        if (deleted) {
            // Returning a simple string message when deletion is successful
            return "Customer deleted successfully";
        } else {
            // Throwing an exception if the customer could not be deleted (e.g., not found)
            throw new RuntimeException("Customer not found with ID: " + id);
        }
    }

//    @GetMapping("/getPhone")
//    public Long getCustomerIdByPhoneNumber(String phoneNumber) {
//        Customer customer = customerRepository.findByPhone(phoneNumber)
//                .orElseThrow(() -> new RuntimeException("Customer not found for phone number: " + phoneNumber));
//        return customer.getId();
//    }

}
