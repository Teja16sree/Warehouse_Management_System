package com.wms.service;

import com.wms.entity.Warehouse;
import com.wms.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    /**
     * Create a new warehouse with validation
     * 
     * @param warehouse the warehouse to create
     * @return the created warehouse
     * @throws IllegalArgumentException if name or location is null/empty
     */
    public Warehouse createWarehouse(Warehouse warehouse) {
        if (warehouse.getWarehouseName() == null || warehouse.getWarehouseName().trim().isEmpty()) {
            throw new IllegalArgumentException("Warehouse name cannot be null or empty");
        }
        if (warehouse.getLocation() == null || warehouse.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Warehouse location cannot be null or empty");
        }
        return warehouseRepository.save(warehouse);
    }

    /**
     * Get all warehouses
     * 
     * @return list of all warehouses
     */
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    /**
     * Get warehouse by ID
     * 
     * @param id the warehouse ID
     * @return optional containing the warehouse if found
     * @throws IllegalArgumentException if ID is invalid
     */
    public Optional<Warehouse> getWarehouseById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid warehouse ID");
        }
        return warehouseRepository.findById(id);
    }

    /**
     * Update an existing warehouse
     * 
     * @param id               the warehouse ID
     * @param warehouseDetails the updated warehouse details
     * @return the updated warehouse
     * @throws IllegalArgumentException if ID is invalid
     * @throws RuntimeException         if warehouse not found
     */
    public Warehouse updateWarehouse(Long id, Warehouse warehouseDetails) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid warehouse ID");
        }
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        if (warehouse.isPresent()) {
            Warehouse existingWarehouse = warehouse.get();
            if (warehouseDetails.getWarehouseName() != null && !warehouseDetails.getWarehouseName().trim().isEmpty()) {
                existingWarehouse.setWarehouseName(warehouseDetails.getWarehouseName());
            }
            if (warehouseDetails.getLocation() != null && !warehouseDetails.getLocation().trim().isEmpty()) {
                existingWarehouse.setLocation(warehouseDetails.getLocation());
            }
            return warehouseRepository.save(existingWarehouse);
        }
        throw new RuntimeException("Warehouse not found with ID: " + id);
    }

    /**
     * Delete a warehouse
     * 
     * @param id the warehouse ID
     * @throws IllegalArgumentException if ID is invalid
     * @throws RuntimeException         if warehouse not found
     */
    public void deleteWarehouse(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid warehouse ID");
        }
        if (!warehouseRepository.existsById(id)) {
            throw new RuntimeException("Warehouse not found with ID: " + id);
        }
        warehouseRepository.deleteById(id);
    }
}
