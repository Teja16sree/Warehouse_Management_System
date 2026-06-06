package com.wms.service;

import com.wms.entity.Order;
import com.wms.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Create a new order with validation
     */
    public Order createOrder(Order order) {
        if (order.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (order.getQuantity() == null || order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        return orderRepository.save(order);
    }

    /**
     * Get all orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Get order by ID
     */
    public Order getOrderById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }
}