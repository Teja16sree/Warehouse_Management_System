package com.wms.controller;

import com.wms.service.BarcodeService;
import com.wms.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductQRCodeController {

    private final ProductService productService;
    private final BarcodeService barcodeService;

    public ProductQRCodeController(ProductService productService, BarcodeService barcodeService) {
        this.productService = productService;
        this.barcodeService = barcodeService;
    }

    @GetMapping("/{id}/qrcode")
    public ResponseEntity<?> generateQRCode(
            @PathVariable Long id) {

        return productService.getProductById(id)
                .map(product -> {
                    try {
                        return ResponseEntity.ok(barcodeService.generateQRCode(product.getSku()));
                    } catch (Exception e) {
                        return ResponseEntity.internalServerError().build();
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
