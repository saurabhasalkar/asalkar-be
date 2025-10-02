package com.asalkar.healthyhub.entity.commerce;

import com.asalkar.healthyhub.entity.catalog.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "wishlist_item", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"wishlist_id", "product_id"})
})
public class WishlistItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_item_id")
    private Long wishlistItemId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id", nullable = false)
    private Wishlist wishlist;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    
    public Long getWishlistItemId() { return wishlistItemId; }
    public void setWishlistItemId(Long wishlistItemId) { this.wishlistItemId = wishlistItemId; }
    
    public Wishlist getWishlist() { return wishlist; }
    public void setWishlist(Wishlist wishlist) { this.wishlist = wishlist; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}