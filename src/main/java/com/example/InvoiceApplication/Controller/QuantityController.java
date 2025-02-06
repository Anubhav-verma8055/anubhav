package com.example.InvoiceApplication.Controller;

import com.example.InvoiceApplication.Entity.Quantity;
import com.example.InvoiceApplication.Service.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/quantity")
public class QuantityController {

    @Autowired
    private QuantityService quantityService;

    // Create or update quantity
    @PostMapping("/{itemId}")
    public Quantity createOrUpdateQuantity(@PathVariable Long itemId, @RequestBody Quantity quantity) {
        quantity.setItemId(itemId); // Ensure the itemId is set in the request body
        return quantityService.createOrUpdateQuantity(itemId, quantity);
    }

    // Get quantity by itemId
    @GetMapping("/{itemId}")
    public Optional<Quantity> getQuantityByItemId(@PathVariable Long itemId) {
        return quantityService.getQuantityByItemId(itemId);
    }

    // Update available quantity
    @PutMapping("/available/{itemId}")
    public Quantity updateAvailableQuantity(@PathVariable Long itemId, @RequestParam Long availableQuantity) {
        return quantityService.updateAvailableQuantity(itemId, availableQuantity);
    }

    // Update requested quantity
    @PutMapping("/requested/{itemId}")
    public Quantity updateRequestedQuantity(@PathVariable Long itemId, @RequestParam Long requestedQuantity) {
        return quantityService.updateRequestedQuantity(itemId, requestedQuantity);
    }
}
