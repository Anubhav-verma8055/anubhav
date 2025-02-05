package com.example.InvoiceApplication.Service;

import com.example.InvoiceApplication.Entity.Item;
import com.example.InvoiceApplication.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    // Create a new item
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // Get all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Get a specific item by ID
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    // Update an existing item
    public Item updateItem(Long id, Item itemDetails) {
        if (itemRepository.existsById(id)) {
            itemDetails.setItemId(id);  // Ensure the ID is retained for the update
            return itemRepository.save(itemDetails);
        }
        return null;  // Item not found
    }

    // Delete an item by ID
    public boolean deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;  // Item not found
    }

}
