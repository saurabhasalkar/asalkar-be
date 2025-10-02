package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.entity.auth.User;
import com.asalkar.healthyhub.entity.common.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    public User getCurrentUser() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public User updateProfile(User updateRequest) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public List<Address> getUserAddresses() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Address createAddress(Address address) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Address updateAddress(Long addressId, Address address) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public void deleteAddress(Long addressId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Page<User> searchUsers(String q, String role, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public User getUserById(Long userId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public User updateUser(Long userId, User updateRequest) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}