package com.wms.service;

import com.wms.dto.ReceivingRequest;
import com.wms.entity.InventoryItem;
import com.wms.entity.Product;
import com.wms.entity.StorageBin;
import com.wms.repository.InventoryItemRepository;
import com.wms.repository.ProductRepository;
import com.wms.repository.StorageBinRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ReceivingService {

    private final InventoryItemRepository inventoryRepo;
    private final ProductRepository productRepo;
    private final StorageBinRepository storageRepo;

    public ReceivingService(
            InventoryItemRepository inventoryRepo,
            ProductRepository productRepo,
            StorageBinRepository storageRepo) {

        this.inventoryRepo = inventoryRepo;
        this.productRepo = productRepo;
        this.storageRepo = storageRepo;
    }

    /**
     * Receive shipment and create inventory item
     * 
     * @param request the receiving request containing product ID, storage bin ID,
     *                and quantity
     * @return the created inventory item
     * @throws IllegalArgumentException if request data is invalid
     * @throws RuntimeException         if product or storage bin not found
     */
    @Transactional
    public InventoryItem receiveShipment(ReceivingRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Receiving request cannot be null");
        }
        if (request.getProductId() == null || request.getProductId() <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        if (request.getStorageBinId() == null || request.getStorageBinId() <= 0) {
            throw new IllegalArgumentException("Invalid storage bin ID");
        }
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + request.getProductId()));

        StorageBin storageBin = storageRepo.findById(request.getStorageBinId())
                .orElseThrow(() -> new RuntimeException("Storage Bin not found with ID: " + request.getStorageBinId()));

        InventoryItem item = new InventoryItem();

        item.setProduct(product);
        item.setStorageBin(storageBin);
        item.setQuantity(request.getQuantity());

        return inventoryRepo.save(item);
    }
}
