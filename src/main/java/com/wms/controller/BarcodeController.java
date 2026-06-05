package com.wms.controller;

import com.wms.service.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barcode")
public class BarcodeController {

    @Autowired
    private BarcodeService barcodeService;

    @GetMapping("/{sku}")
    public String generate(@PathVariable String sku)
            throws Exception {

        return barcodeService.generateQRCode(sku);
    }
}
