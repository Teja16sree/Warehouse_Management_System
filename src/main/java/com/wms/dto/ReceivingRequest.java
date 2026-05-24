package com.wms.dto;

import lombok.Data;

@Data
public class ReceivingRequest {

    private Long productId;

    private Long storageBinId;

    private Integer quantity;
}
