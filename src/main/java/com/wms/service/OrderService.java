package com.wms.service;

import com.wms.entity.InventoryItem;
import com.wms.entity.Order;
import com.wms.entity.Product;
import com.wms.exception.InsufficientStockException;
import com.wms.repository.InventoryItemRepository;
import com.wms.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryItemRepository inventoryItemRepository;

    public OrderService(OrderRepository orderRepository,
            InventoryItemRepository inventoryItemRepository) {
        this.orderRepository = orderRepository;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    /**
     * Create a new order
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

    /**
     * Update Order Status
     */
    public Order updateOrderStatus(Long orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        if ("PACKED".equalsIgnoreCase(status)) {

            Product product = order.getProduct();

            InventoryItem inventory = inventoryItemRepository.findByProduct(product);

            if (inventory == null) {
                throw new RuntimeException(
                        "Inventory not found for product");
            }

            // Check stock availability
            if (inventory.getQuantity() < order.getQuantity()) {

                throw new InsufficientStockException(
                        "Not enough stock available. Available: "
                                + inventory.getQuantity()
                                + ", Requested: "
                                + order.getQuantity());
            }

            // Deduct inventory
            inventory.setQuantity(
                    inventory.getQuantity()
                            - order.getQuantity());

            inventoryItemRepository.save(inventory);
        }

        return orderRepository.save(order);
    }
}