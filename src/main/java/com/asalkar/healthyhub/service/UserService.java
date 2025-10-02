package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.entity.auth.User;
import com.asalkar.healthyhub.entity.common.Address;
import com.asalkar.healthyhub.repository.AddressRepository;
import com.asalkar.healthyhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    public User getCurrentUser() {
        return userRepository.findById(2L)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public User updateProfile(User updateRequest) {
        User user = getCurrentUser();
        if (updateRequest.getFirstName() != null) user.setFirstName(updateRequest.getFirstName());
        if (updateRequest.getLastName() != null) user.setLastName(updateRequest.getLastName());
        if (updateRequest.getPhone() != null) user.setPhone(updateRequest.getPhone());
        return userRepository.save(user);
    }
    
    public List<Address> getUserAddresses() {
        return addressRepository.findByUserUserIdOrderByIsDefaultDescCreatedAtDesc(2L);
    }
    
    public Address createAddress(Address address) {
        User user = getCurrentUser();
        address.setUser(user);
        return addressRepository.save(address);
    }
    
    public Address updateAddress(Long addressId, Address address) {
        Address existing = addressRepository.findById(addressId)
            .orElseThrow(() -> new RuntimeException("Address not found"));
        if (address.getName() != null) existing.setName(address.getName());
        if (address.getLine1() != null) existing.setLine1(address.getLine1());
        if (address.getCity() != null) existing.setCity(address.getCity());
        if (address.getState() != null) existing.setState(address.getState());
        if (address.getPostalCode() != null) existing.setPostalCode(address.getPostalCode());
        return addressRepository.save(existing);
    }
    
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
    
    public Page<User> searchUsers(String q, String role, Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public User updateUser(Long userId, User updateRequest) {
        User user = getUserById(userId);
        if (updateRequest.getIsActive() != null) user.setIsActive(updateRequest.getIsActive());
        return userRepository.save(user);
    }
}