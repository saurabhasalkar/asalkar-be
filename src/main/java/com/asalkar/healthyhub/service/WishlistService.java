package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.entity.catalog.Product;
import com.asalkar.healthyhub.entity.commerce.Wishlist;
import com.asalkar.healthyhub.entity.commerce.WishlistItem;
import com.asalkar.healthyhub.repository.ProductRepository;
import com.asalkar.healthyhub.repository.WishlistRepository;
import com.asalkar.healthyhub.repository.WishlistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {
    
    @Autowired
    private WishlistRepository wishlistRepository;
    
    @Autowired
    private WishlistItemRepository wishlistItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public Wishlist getCurrentUserWishlist() {
        Long userId = 2L; // Hardcoded for now
        return wishlistRepository.findByUserUserId(userId)
            .orElseGet(() -> {
                Wishlist wishlist = new Wishlist();
                wishlist.setUser(new com.asalkar.healthyhub.entity.auth.User());
                wishlist.getUser().setUserId(userId);
                return wishlistRepository.save(wishlist);
            });
    }
    
    public WishlistItem addToWishlist(Long productId) {
        Wishlist wishlist = getCurrentUserWishlist();
        Optional<Product> product = productRepository.findById(productId);
        
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
        
        WishlistItem item = new WishlistItem();
        item.setWishlist(wishlist);
        item.setProduct(product.get());
        
        return wishlistItemRepository.save(item);
    }
    
    public void removeFromWishlist(Long itemId) {
        wishlistItemRepository.deleteById(itemId);
    }
}