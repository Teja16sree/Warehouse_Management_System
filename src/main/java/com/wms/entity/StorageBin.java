package com.wms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "storage_bins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageBin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String binCode;

    private String storageType;

    @Column(nullable = false)
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
}
