package com.asalkar.healthyhub.entity.commerce;

import com.asalkar.healthyhub.entity.auth.User;
import com.asalkar.healthyhub.entity.common.BaseEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "status", length = 20)
    private String status = "ACTIVE";
    
    public Cart() {
        this.status = "ACTIVE";
    }
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;
    
    public Long getCartId() { return cartId; }
    public void setCartId(Long cartId) { this.cartId = cartId; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }
}