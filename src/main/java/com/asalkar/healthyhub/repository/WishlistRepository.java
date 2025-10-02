package com.asalkar.healthyhub.repository;

import com.asalkar.healthyhub.entity.commerce.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUserUserId(Long userId);
}