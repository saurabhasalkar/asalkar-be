package com.asalkar.healthyhub.entity.commerce;

import com.asalkar.healthyhub.entity.catalog.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_item", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cart_id", "product_id"})
})
public class CartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long cartItemId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    
    @Column(name = "unit_price_at_add", precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPriceAtAdd;
    
    @Column(name = "quantity", nullable = false)
    @Positive(message = "Quantity must be positive")
    private Integer quantity;
    
    public CartItem() {}
    
    public CartItem(Cart cart, Product product, BigDecimal unitPriceAtAdd, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.cart = cart;
        this.product = product;
        this.unitPriceAtAdd = unitPriceAtAdd;
        this.quantity = quantity;
    }
    
    public Long getCartItemId() { return cartItemId; }
    public void setCartItemId(Long cartItemId) { this.cartItemId = cartItemId; }
    
    public Cart getCart() { return cart; }
    public void setCart(Cart cart) { this.cart = cart; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public BigDecimal getUnitPriceAtAdd() { return unitPriceAtAdd; }
    public void setUnitPriceAtAdd(BigDecimal unitPriceAtAdd) { this.unitPriceAtAdd = unitPriceAtAdd; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}