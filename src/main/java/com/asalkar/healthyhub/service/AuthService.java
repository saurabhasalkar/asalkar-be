package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.dto.auth.LoginRequest;
import com.asalkar.healthyhub.dto.auth.RegisterRequest;
import com.asalkar.healthyhub.entity.auth.Role;
import com.asalkar.healthyhub.entity.auth.User;
import com.asalkar.healthyhub.repository.RoleRepository;
import com.asalkar.healthyhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User register(RegisterRequest request) {
        // Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists with email: " + request.getEmail());
        }
        
        // Create new user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setIsActive(true);
        
        // Assign default CUSTOMER role
        Set<Role> roles = new HashSet<>();
        Optional<Role> customerRole = roleRepository.findByName("CUSTOMER");
        if (customerRole.isPresent()) {
            roles.add(customerRole.get());
            user.setRoles(roles);
        }
        
        return userRepository.save(user);
    }
    
    public String login(LoginRequest request) {
        // Find user by email
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }
        
        User user = userOpt.get();
        
        // Check if user is active
        if (!user.getIsActive()) {
            throw new RuntimeException("User account is deactivated");
        }
        
        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }
        
        // For now, return a simple token (in production, use JWT)
        return "Bearer token-" + user.getUserId() + "-" + System.currentTimeMillis();
    }
    
    public void logout() {
        // For stateless JWT tokens, logout is typically handled client-side
        // In a real implementation, you might:
        // 1. Add token to blacklist
        // 2. Clear server-side sessions
        // 3. Invalidate refresh tokens
        
        // For now, just return success (client should discard token)
    }
    
    public User createAdmin(RegisterRequest request) {
        // Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists with email: " + request.getEmail());
        }
        
        // Create new admin user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setIsActive(true);
        
        // Assign ADMIN role
        Set<Role> roles = new HashSet<>();
        Optional<Role> adminRole = roleRepository.findByName("ADMIN");
        if (adminRole.isPresent()) {
            roles.add(adminRole.get());
            user.setRoles(roles);
        }
        
        return userRepository.save(user);
    }
}