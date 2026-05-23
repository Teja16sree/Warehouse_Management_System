package com.wms.controller;

import com.wms.entity.InventoryItem;
import com.wms.service.InventoryItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory-items")
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    public InventoryItemController(InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @PostMapping
    public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody InventoryItem inventoryItem) {
        try {
            InventoryItem createdItem = inventoryItemService.createInventoryItem(inventoryItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems() {
        List<InventoryItem> items = inventoryItemService.getAllInventoryItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable Long id) {
        try {
            Optional<InventoryItem> item = inventoryItemService.getInventoryItemById(id);
            return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItem> updateInventoryItem(@PathVariable Long id,
            @RequestBody InventoryItem itemDetails) {
        try {
            InventoryItem updatedItem = inventoryItemService.updateInventoryItem(id, itemDetails);
            return ResponseEntity.ok(updatedItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventoryItem(@PathVariable Long id) {
        try {
            inventoryItemService.deleteInventoryItem(id);
            return ResponseEntity.ok("Inventory item deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/add-quantity")
    public ResponseEntity<InventoryItem> addQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        try {
            InventoryItem updatedItem = inventoryItemService.addQuantity(id, quantity);
            return ResponseEntity.ok(updatedItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/remove-quantity")
    public ResponseEntity<InventoryItem> removeQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        try {
            InventoryItem updatedItem = inventoryItemService.removeQuantity(id, quantity);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
