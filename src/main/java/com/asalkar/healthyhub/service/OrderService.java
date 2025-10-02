package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.entity.commerce.Order;
import com.asalkar.healthyhub.entity.commerce.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    
    public Order createOrderFromCart(Long shippingAddressId, Long billingAddressId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Page<Order> getCurrentUserOrders(Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Order getOrderById(Long orderId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Order getOrderByNumber(String orderNumber) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Page<Order> searchOrders(String status, String q, String dateFrom, String dateTo, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Order updateOrderStatus(Long orderId, String statusCode) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public List<OrderItem> getOrderItems(Long orderId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}