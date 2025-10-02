package com.asalkar.healthyhub.repository;

import com.asalkar.healthyhub.entity.commerce.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
}