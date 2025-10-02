package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.entity.auth.User;
import com.asalkar.healthyhub.entity.catalog.Product;
import com.asalkar.healthyhub.entity.commerce.Cart;
import com.asalkar.healthyhub.entity.commerce.CartItem;
import com.asalkar.healthyhub.repository.CartItemRepository;
import com.asalkar.healthyhub.repository.CartRepository;
import com.asalkar.healthyhub.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public Cart getCurrentUserCart() {
        Long userId = 2L; // Hardcoded for now
        return cartRepository.findByUserUserIdAndStatus(userId, "ACTIVE")
            .orElseGet(() -> {
                Cart cart = new Cart();
                User user = new User();
                user.setUserId(userId);
                cart.setUser(user);
                return cartRepository.save(cart);
            });
    }
    
    public CartItem addToCart(Long productId, Integer quantity) {
        Cart cart = getCurrentUserCart();
        Optional<Product> product = productRepository.findById(productId);
        
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
        
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product.get());
        item.setQuantity(quantity);
        item.setUnitPriceAtAdd(product.get().getPriceSale() != null ? 
            product.get().getPriceSale() : product.get().getPriceMrp());
        
        return cartItemRepository.save(item);
    }
    
    public CartItem updateCartItem(Long itemId, Integer quantity) {
        CartItem item = cartItemRepository.findById(itemId)
            .orElseThrow(() -> new IllegalArgumentException("Cart item not found with ID: " + itemId));
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }
    
    public void removeFromCart(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }
}