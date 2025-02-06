package com.example.InvoiceApplication.Service;

import com.example.InvoiceApplication.Entity.Quantity;
import com.example.InvoiceApplication.Repository.QuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuantityService {

    @Autowired
    private QuantityRepository quantityRepository;

    // Create or update quantity

    public Quantity createOrUpdateQuantity(Long itemId, Quantity quantity) {
        Optional<Quantity> existingQuantity = quantityRepository.findById(itemId);

        if (existingQuantity.isPresent()) {
            Quantity existing = existingQuantity.get();
            existing.setAvailableQuantity(quantity.getAvailableQuantity());
            existing.setRequestedQuantity(quantity.getRequestedQuantity());
            return quantityRepository.save(existing);
        } else {
            return quantityRepository.save(quantity);  // If the item does not exist, create a new entry
        }
    }

    // Get Quantity by itemId
    public Optional<Quantity> getQuantityByItemId(Long itemId) {
        return quantityRepository.findById(itemId);
    }

    // Update Available Quantity
    public Quantity updateAvailableQuantity(Long itemId, Long availableQuantity) {
        Optional<Quantity> quantity = quantityRepository.findById(itemId);

        if (quantity.isPresent()) {
            Quantity existing = quantity.get();
            existing.setAvailableQuantity(availableQuantity);
            return quantityRepository.save(existing);
        }
        return null; // Item not found
    }

    // Update Requested Quantity
    public Quantity updateRequestedQuantity(Long itemId, Long requestedQuantity) {
        Optional<Quantity> quantity = quantityRepository.findById(itemId);

        if (quantity.isPresent()) {
            Quantity existing = quantity.get();
            existing.setRequestedQuantity(requestedQuantity);
            return quantityRepository.save(existing);
        }
        return null; // Item not found
    }


}
