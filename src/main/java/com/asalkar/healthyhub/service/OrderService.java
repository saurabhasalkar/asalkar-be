package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.entity.commerce.Order;
import com.asalkar.healthyhub.entity.commerce.OrderItem;
import com.asalkar.healthyhub.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public Order createOrderFromCart(Long shippingAddressId, Long billingAddressId) {
        throw new UnsupportedOperationException("Order creation not implemented yet");
    }
    
    public Page<Order> getCurrentUserOrders(Pageable pageable) {
        return orderRepository.findByUserUserIdOrderByCreatedAtDesc(2L, pageable);
    }
    
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
    public Order getOrderByNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
    public Page<Order> searchOrders(String status, String q, String dateFrom, String dateTo, Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
    
    public Order updateOrderStatus(Long orderId, String statusCode) {
        Order order = getOrderById(orderId);
        // Status update logic would go here
        return orderRepository.save(order);
    }
    
    public List<OrderItem> getOrderItems(Long orderId) {
        Order order = getOrderById(orderId);
        return order.getOrderItems();
    }
}