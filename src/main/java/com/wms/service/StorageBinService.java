package com.wms.service;

import com.wms.entity.StorageBin;
import com.wms.repository.StorageBinRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StorageBinService {

    private final StorageBinRepository storageBinRepository;

    public StorageBinService(StorageBinRepository storageBinRepository) {
        this.storageBinRepository = storageBinRepository;
    }

    /**
     * Create a new storage bin with validation
     * 
     * @param storageBin the storage bin to create
     * @return the created storage bin
     * @throws IllegalArgumentException if bin code or capacity is invalid
     */
    public StorageBin createStorageBin(StorageBin storageBin) {
        if (storageBin.getBinCode() == null || storageBin.getBinCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Bin code cannot be null or empty");
        }
        if (storageBin.getCapacity() == null || storageBin.getCapacity() <= 0) {
            throw new IllegalArgumentException("Bin capacity must be greater than 0");
        }
        return storageBinRepository.save(storageBin);
    }

    /**
     * Get all storage bins
     * 
     * @return list of all storage bins
     */
    public List<StorageBin> getAllStorageBins() {
        return storageBinRepository.findAll();
    }

    /**
     * Get storage bin by ID
     * 
     * @param id the storage bin ID
     * @return optional containing the storage bin if found
     * @throws IllegalArgumentException if ID is invalid
     */
    public Optional<StorageBin> getStorageBinById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid storage bin ID");
        }
        return storageBinRepository.findById(id);
    }

    /**
     * Update an existing storage bin
     * 
     * @param id         the storage bin ID
     * @param binDetails the updated storage bin details
     * @return the updated storage bin
     * @throws IllegalArgumentException if ID is invalid
     * @throws RuntimeException         if storage bin not found
     */
    public StorageBin updateStorageBin(Long id, StorageBin binDetails) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid storage bin ID");
        }
        Optional<StorageBin> bin = storageBinRepository.findById(id);
        if (bin.isPresent()) {
            StorageBin existingBin = bin.get();
            if (binDetails.getBinCode() != null && !binDetails.getBinCode().trim().isEmpty()) {
                existingBin.setBinCode(binDetails.getBinCode());
            }
            if (binDetails.getStorageType() != null && !binDetails.getStorageType().trim().isEmpty()) {
                existingBin.setStorageType(binDetails.getStorageType());
            }
            if (binDetails.getCapacity() != null && binDetails.getCapacity() > 0) {
                existingBin.setCapacity(binDetails.getCapacity());
            }
            return storageBinRepository.save(existingBin);
        }
        throw new RuntimeException("Storage bin not found with ID: " + id);
    }

    /**
     * Delete a storage bin
     * 
     * @param id the storage bin ID
     * @throws IllegalArgumentException if ID is invalid
     * @throws RuntimeException         if storage bin not found
     */
    public void deleteStorageBin(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid storage bin ID");
        }
        if (!storageBinRepository.existsById(id)) {
            throw new RuntimeException("Storage bin not found with ID: " + id);
        }
        storageBinRepository.deleteById(id);
    }
}
