package com.asalkar.healthyhub.repository;

import com.asalkar.healthyhub.entity.commerce.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}