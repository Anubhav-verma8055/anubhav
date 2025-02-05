package com.example.InvoiceApplication.Service;

import com.example.InvoiceApplication.Entity.Customer;
import com.example.InvoiceApplication.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get a customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Update an existing customer
    public Customer updateCustomer(Long id, Customer customerDetails) {
        if (customerRepository.existsById(id)) {
            customerDetails.setId(id);  // Ensure the ID is retained for the update
            return customerRepository.save(customerDetails);
        }
        return null;  // Customer not found
    }

    // Delete a customer
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;  // Customer not found
    }


}
