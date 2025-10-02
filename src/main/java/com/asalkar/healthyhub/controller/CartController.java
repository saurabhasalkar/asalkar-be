package com.asalkar.healthyhub.controller;

import com.asalkar.healthyhub.dto.commerce.CartItemRequest;
import com.asalkar.healthyhub.dto.commerce.CreateOrderRequest;
import com.asalkar.healthyhub.entity.commerce.Cart;
import com.asalkar.healthyhub.entity.commerce.CartItem;
import com.asalkar.healthyhub.entity.commerce.Order;
import com.asalkar.healthyhub.service.CartService;
import com.asalkar.healthyhub.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/cart")
    public ResponseEntity<Cart> getCart() {
        Cart cart = cartService.getCurrentUserCart();
        return ResponseEntity.ok(cart);
    }
    
    @PostMapping("/cart/items")
    public ResponseEntity<CartItem> addToCart(@Valid @RequestBody CartItemRequest request) {
        CartItem item = cartService.addToCart(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(item);
    }
    
    @PutMapping("/cart/items/{itemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long itemId, @RequestBody Map<String, Integer> request) {
        Integer quantity = request.get("quantity");
        CartItem item = cartService.updateCartItem(itemId, quantity);
        return ResponseEntity.ok(item);
    }
    
    @DeleteMapping("/cart/items/{itemId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long itemId) {
        cartService.removeFromCart(itemId);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrderFromCart(request.getShippingAddressId(), request.getBillingAddressId());
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/orders")
    public ResponseEntity<Page<Order>> getMyOrders(Pageable pageable) {
        Page<Order> orders = orderService.getCurrentUserOrders(pageable);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/orders/by-number/{orderNumber}")
    public ResponseEntity<Order> getOrderByNumber(@PathVariable String orderNumber) {
        Order order = orderService.getOrderByNumber(orderNumber);
        return ResponseEntity.ok(order);
    }
}