package com.wms.service;

import com.wms.entity.InventoryItem;
import com.wms.repository.InventoryItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    /**
     * Create a new inventory item
     */
    public InventoryItem createInventoryItem(InventoryItem inventoryItem) {
        if (inventoryItem.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (inventoryItem.getStorageBin() == null) {
            throw new IllegalArgumentException("Storage bin cannot be null");
        }
        if (inventoryItem.getQuantity() == null || inventoryItem.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be null or negative");
        }
        return inventoryItemRepository.save(inventoryItem);
    }

    /**
     * Get all inventory items
     */
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    /**
     * Get inventory item by ID
     */
    public Optional<InventoryItem> getInventoryItemById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid inventory item ID");
        }
        return inventoryItemRepository.findById(id);
    }

    /**
     * Update an existing inventory item
     */
    public InventoryItem updateInventoryItem(Long id, InventoryItem itemDetails) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid inventory item ID");
        }
        Optional<InventoryItem> item = inventoryItemRepository.findById(id);
        if (item.isPresent()) {
            InventoryItem existingItem = item.get();
            if (itemDetails.getQuantity() != null && itemDetails.getQuantity() >= 0) {
                existingItem.setQuantity(itemDetails.getQuantity());
            }
            if (itemDetails.getProduct() != null) {
                existingItem.setProduct(itemDetails.getProduct());
            }
            if (itemDetails.getStorageBin() != null) {
                existingItem.setStorageBin(itemDetails.getStorageBin());
            }
            return inventoryItemRepository.save(existingItem);
        }
        throw new RuntimeException("Inventory item not found with ID: " + id);
    }

    /**
     * Delete an inventory item
     */
    public void deleteInventoryItem(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid inventory item ID");
        }
        if (!inventoryItemRepository.existsById(id)) {
            throw new RuntimeException("Inventory item not found with ID: " + id);
        }
        inventoryItemRepository.deleteById(id);
    }

    /**
     * Add quantity to inventory item
     */
    public InventoryItem addQuantity(Long id, Integer quantityToAdd) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid inventory item ID");
        }
        if (quantityToAdd == null || quantityToAdd <= 0) {
            throw new IllegalArgumentException("Quantity to add must be greater than 0");
        }
        Optional<InventoryItem> item = inventoryItemRepository.findById(id);
        if (item.isPresent()) {
            InventoryItem inventoryItem = item.get();
            inventoryItem.setQuantity(inventoryItem.getQuantity() + quantityToAdd);
            return inventoryItemRepository.save(inventoryItem);
        }
        throw new RuntimeException("Inventory item not found with ID: " + id);
    }

    /**
     * Remove quantity from inventory item
     */
    public InventoryItem removeQuantity(Long id, Integer quantityToRemove) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid inventory item ID");
        }
        if (quantityToRemove == null || quantityToRemove <= 0) {
            throw new IllegalArgumentException("Quantity to remove must be greater than 0");
        }
        Optional<InventoryItem> item = inventoryItemRepository.findById(id);
        if (item.isPresent()) {
            InventoryItem inventoryItem = item.get();
            if (inventoryItem.getQuantity() < quantityToRemove) {
                throw new IllegalArgumentException("Insufficient quantity available");
            }
            inventoryItem.setQuantity(inventoryItem.getQuantity() - quantityToRemove);
            return inventoryItemRepository.save(inventoryItem);
        }
        throw new RuntimeException("Inventory item not found with ID: " + id);
    }
}
