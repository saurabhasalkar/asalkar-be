package com.asalkar.healthyhub.repository;

import com.asalkar.healthyhub.entity.catalog.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySlug(String slug);
    Optional<Product> findBySku(String sku);
    List<Product> findByIsActiveTrue();
    
    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId AND p.isActive = true")
    List<Product> findActiveByCategoryId(Long categoryId);
}