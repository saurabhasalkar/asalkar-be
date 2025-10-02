package com.asalkar.healthyhub.controller;

import com.asalkar.healthyhub.entity.commerce.Wishlist;
import com.asalkar.healthyhub.entity.commerce.WishlistItem;
import com.asalkar.healthyhub.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    
    @Autowired
    private WishlistService wishlistService;
    
    @GetMapping
    public ResponseEntity<Wishlist> getWishlist() {
        Wishlist wishlist = wishlistService.getCurrentUserWishlist();
        return ResponseEntity.ok(wishlist);
    }
    
    @PostMapping("/items")
    public ResponseEntity<WishlistItem> addToWishlist(@RequestBody Map<String, Long> request) {
        Long productId = request.get("productId");
        WishlistItem item = wishlistService.addToWishlist(productId);
        return ResponseEntity.ok(item);
    }
    
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<?> removeFromWishlist(@PathVariable Long itemId) {
        wishlistService.removeFromWishlist(itemId);
        return ResponseEntity.ok().build();
    }
}