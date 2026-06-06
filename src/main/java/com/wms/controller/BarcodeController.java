package com.wms.controller;

import com.wms.service.BarcodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barcode")
public class BarcodeController {

    private final BarcodeService barcodeService;

    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @GetMapping("/{sku}")
    public ResponseEntity<String> generate(@PathVariable String sku) {
        try {
            if (sku == null || sku.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            String qrCodePath = barcodeService.generateQRCode(sku);
            return ResponseEntity.ok(qrCodePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
