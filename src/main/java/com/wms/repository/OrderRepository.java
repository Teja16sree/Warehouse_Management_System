package com.wms.repository;

import com.wms.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

}
// save findAll findById Delete methods you get here.
// spring generates them
