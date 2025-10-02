package com.asalkar.healthyhub.repository;

import com.asalkar.healthyhub.entity.commerce.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserUserIdAndStatus(Long userId, String status);
}