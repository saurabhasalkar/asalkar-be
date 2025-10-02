package com.asalkar.healthyhub.controller;

import com.asalkar.healthyhub.dto.auth.LoginRequest;
import com.asalkar.healthyhub.dto.auth.RegisterRequest;
import com.asalkar.healthyhub.entity.auth.User;
import com.asalkar.healthyhub.entity.common.Address;
import com.asalkar.healthyhub.service.AuthService;
import com.asalkar.healthyhub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }
    
    @PostMapping("/auth/logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/auth/create-admin")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody RegisterRequest request) {
        User admin = authService.createAdmin(request);
        return ResponseEntity.ok(admin);
    }
    
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/me")
    public ResponseEntity<User> updateProfile(@RequestBody User updateRequest) {
        User user = userService.updateProfile(updateRequest);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/me/addresses")
    public ResponseEntity<List<Address>> getMyAddresses() {
        List<Address> addresses = userService.getUserAddresses();
        return ResponseEntity.ok(addresses);
    }
    
    @PostMapping("/me/addresses")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody Address address) {
        Address created = userService.createAddress(address);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/me/addresses/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long addressId, @Valid @RequestBody Address address) {
        Address updated = userService.updateAddress(addressId, address);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/me/addresses/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) {
        userService.deleteAddress(addressId);
        return ResponseEntity.ok().build();
    }
}