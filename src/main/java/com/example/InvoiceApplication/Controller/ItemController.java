package com.example.InvoiceApplication.Controller;

import com.example.InvoiceApplication.Entity.Item;
import com.example.InvoiceApplication.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Create a new item
    @PostMapping
    public String createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return "Item created successfully with ID: " + createdItem.getItemId();
    }

    // Get all items
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // Get a specific item by ID
    @GetMapping("/{id}")
    public String getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.getItemById(id);
        if (item.isPresent()) {
            return "Item found: " + item.get().getItemDescription(); // Return a basic string or item details
        } else {
            return "Item not found with ID: " + id;  // Item not found
        }
    }

    // Update an existing item
    @PutMapping("/{id}")
    public String updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Item updatedItem = itemService.updateItem(id, itemDetails);
        if (updatedItem != null) {
            return "Item updated successfully with ID: " + updatedItem.getItemId();
        } else {
            return "Item not found with ID: " + id;  // Item not found
        }
    }

    // Delete an item by ID
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        boolean deleted = itemService.deleteItem(id);
        if (deleted) {
            return "Item deleted successfully with ID: " + id;
        } else {
            return "Item not found with ID: " + id;  // Item not found
        }
    }
}
