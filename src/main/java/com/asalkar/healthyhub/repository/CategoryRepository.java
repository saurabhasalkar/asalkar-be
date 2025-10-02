package com.asalkar.healthyhub.repository;

import com.asalkar.healthyhub.entity.catalog.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIsActiveTrueOrderByName();
    List<Category> findByParentIsNullAndIsActiveTrue();
}