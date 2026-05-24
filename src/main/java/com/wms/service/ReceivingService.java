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

    @Transactional
    public InventoryItem receiveShipment(ReceivingRequest request) {

        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        StorageBin storageBin = storageRepo.findById(request.getStorageBinId())
                .orElseThrow(() -> new RuntimeException("Storage Bin not found"));

        InventoryItem item = new InventoryItem();

        item.setProduct(product);
        item.setStorageBin(storageBin);
        item.setQuantity(request.getQuantity());

        return inventoryRepo.save(item);
    }
}
