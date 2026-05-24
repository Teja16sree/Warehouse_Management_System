package com.wms.controller;

import com.wms.dto.ReceivingRequest;
import com.wms.entity.InventoryItem;
import com.wms.service.ReceivingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receiving")
public class ReceivingController {

    private final ReceivingService service;

    public ReceivingController(ReceivingService service) {
        this.service = service;
    }

    @PostMapping
    public InventoryItem receiveInventory(
            @RequestBody ReceivingRequest request) {

        return service.receiveShipment(request);
    }
}
