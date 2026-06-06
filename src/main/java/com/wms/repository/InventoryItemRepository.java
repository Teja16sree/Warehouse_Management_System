package com.wms.repository;

import com.wms.entity.InventoryItem;
import com.wms.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    InventoryItem findByProduct(Product product);

}
