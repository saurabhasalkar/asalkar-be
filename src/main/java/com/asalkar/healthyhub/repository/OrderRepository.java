package com.asalkar.healthyhub.repository;

import com.asalkar.healthyhub.entity.commerce.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);
    Page<Order> findByUserUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}