package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.entity.commerce.Cart;
import com.asalkar.healthyhub.entity.commerce.CartItem;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    
    public Cart getCurrentUserCart() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public CartItem addToCart(Long productId, Integer quantity) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public CartItem updateCartItem(Long itemId, Integer quantity) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public void removeFromCart(Long itemId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}