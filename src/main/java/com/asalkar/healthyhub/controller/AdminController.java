package com.asalkar.healthyhub.controller;

import com.asalkar.healthyhub.entity.auth.User;
import com.asalkar.healthyhub.entity.catalog.Brand;
import com.asalkar.healthyhub.entity.catalog.Category;
import com.asalkar.healthyhub.entity.catalog.Product;
import com.asalkar.healthyhub.entity.catalog.ProductImage;
import com.asalkar.healthyhub.entity.commerce.Inventory;
import com.asalkar.healthyhub.entity.commerce.Location;
import com.asalkar.healthyhub.entity.commerce.Order;
import com.asalkar.healthyhub.entity.commerce.OrderItem;
import com.asalkar.healthyhub.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CatalogService catalogService;
    
    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private OrderService orderService;
    
    // Users Management
    @GetMapping("/users")
    public ResponseEntity<Page<User>> getUsers(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String role,
            Pageable pageable) {
        Page<User> users = userService.searchUsers(q, role, pageable);
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updateRequest) {
        User user = userService.updateUser(userId, updateRequest);
        return ResponseEntity.ok(user);
    }
    
    // Categories Management
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        Category created = catalogService.createCategory(category);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody Category category) {
        Category updated = catalogService.updateCategory(id, category);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        catalogService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
    
    // Brands Management
    @PostMapping("/brands")
    public ResponseEntity<Brand> createBrand(@Valid @RequestBody Brand brand) {
        Brand created = catalogService.createBrand(brand);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/brands/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @Valid @RequestBody Brand brand) {
        Brand updated = catalogService.updateBrand(id, brand);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/brands/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id) {
        catalogService.deleteBrand(id);
        return ResponseEntity.ok().build();
    }
    
    // Products Management
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product created = catalogService.createProduct(product);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product updated = catalogService.updateProduct(id, product);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        catalogService.softDeleteProduct(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/products/{id}/images")
    public ResponseEntity<ProductImage> addProductImage(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String url = request.get("url");
        String altText = request.get("altText");
        ProductImage image = catalogService.addProductImage(id, url, altText);
        return ResponseEntity.ok(image);
    }
    
    @DeleteMapping("/products/{id}/images/{imageId}")
    public ResponseEntity<?> deleteProductImage(@PathVariable Long id, @PathVariable Long imageId) {
        catalogService.deleteProductImage(imageId);
        return ResponseEntity.ok().build();
    }
    
    // Locations Management
    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getLocations() {
        List<Location> locations = inventoryService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
    
    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@Valid @RequestBody Location location) {
        Location created = inventoryService.createLocation(location);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/locations/{locationId}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long locationId, @Valid @RequestBody Location location) {
        Location updated = inventoryService.updateLocation(locationId, location);
        return ResponseEntity.ok(updated);
    }
    
    // Inventory Management
    @GetMapping("/inventory")
    public ResponseEntity<List<Inventory>> getInventory(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) String sku,
            @RequestParam(required = false) Long locationId,
            @RequestParam(required = false) String code) {
        List<Inventory> inventory = inventoryService.getInventory(productId, sku, code);
        return ResponseEntity.ok(inventory);
    }
    
    @PostMapping("/inventory/adjust")
    public ResponseEntity<Inventory> adjustInventory(@RequestBody Map<String, Object> request) {
        Long productId = (Long) request.get("productId");
        String sku = (String) request.get("sku");
        Long locationId = (Long) request.get("locationId");
        String code = (String) request.get("code");
        Integer delta = (Integer) request.get("delta");
        String note = (String) request.get("note");
        
        Inventory inventory = inventoryService.adjustInventory(productId, sku, locationId, code, delta, note);
        return ResponseEntity.ok(inventory);
    }
    
    // Orders Management
    @GetMapping("/orders")
    public ResponseEntity<Page<Order>> getOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo,
            Pageable pageable) {
        Page<Order> orders = orderService.searchOrders(status, q, dateFrom, dateTo, pageable);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
    
    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestBody Map<String, String> request) {
        String statusCode = request.get("statusCode");
        Order order = orderService.updateOrderStatus(orderId, statusCode);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/orders/{orderId}/items")
    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable Long orderId) {
        List<OrderItem> items = orderService.getOrderItems(orderId);
        return ResponseEntity.ok(items);
    }
}