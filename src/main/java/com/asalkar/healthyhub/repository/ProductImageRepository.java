package com.asalkar.healthyhub.repository;

import com.asalkar.healthyhub.entity.catalog.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProductProductIdOrderBySortOrder(Long productId);
}