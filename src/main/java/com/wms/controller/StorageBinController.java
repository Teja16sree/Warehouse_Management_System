package com.wms.controller;

import com.wms.entity.StorageBin;
import com.wms.service.StorageBinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/storage-bins")
public class StorageBinController {

    private final StorageBinService storageBinService;

    public StorageBinController(StorageBinService storageBinService) {
        this.storageBinService = storageBinService;
    }

    @PostMapping
    public ResponseEntity<StorageBin> createStorageBin(@RequestBody StorageBin storageBin) {
        try {
            StorageBin createdBin = storageBinService.createStorageBin(storageBin);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<StorageBin>> getAllStorageBins() {
        List<StorageBin> bins = storageBinService.getAllStorageBins();
        return ResponseEntity.ok(bins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageBin> getStorageBinById(@PathVariable Long id) {
        try {
            Optional<StorageBin> bin = storageBinService.getStorageBinById(id);
            return bin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StorageBin> updateStorageBin(@PathVariable Long id, @RequestBody StorageBin binDetails) {
        try {
            StorageBin updatedBin = storageBinService.updateStorageBin(id, binDetails);
            return ResponseEntity.ok(updatedBin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStorageBin(@PathVariable Long id) {
        try {
            storageBinService.deleteStorageBin(id);
            return ResponseEntity.ok("Storage bin deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
