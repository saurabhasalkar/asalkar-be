package com.asalkar.healthyhub.repository;

import com.asalkar.healthyhub.entity.common.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserUserIdOrderByIsDefaultDescCreatedAtDesc(Long userId);
}