package com.asalkar.healthyhub.controller;

import com.asalkar.healthyhub.entity.catalog.Brand;
import com.asalkar.healthyhub.entity.catalog.Category;
import com.asalkar.healthyhub.entity.catalog.Product;
import com.asalkar.healthyhub.entity.catalog.ProductImage;
import com.asalkar.healthyhub.entity.commerce.Inventory;
import com.asalkar.healthyhub.entity.commerce.Location;
import com.asalkar.healthyhub.service.CatalogService;
import com.asalkar.healthyhub.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CatalogController {
    
    @Autowired
    private CatalogService catalogService;
    
    @Autowired
    private InventoryService inventoryService;
    
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = catalogService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getBrands() {
        List<Brand> brands = catalogService.getAllBrands();
        return ResponseEntity.ok(brands);
    }
    
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long brand,
            @RequestParam(required = false) Boolean isActive,
            Pageable pageable) {
        Page<Product> products = catalogService.searchProducts(q, category, brand, isActive, pageable);
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = catalogService.getProductById(id);
        return ResponseEntity.ok(product);
    }
    
    @GetMapping("/products/sku/{sku}")
    public ResponseEntity<Product> getProductBySku(@PathVariable String sku) {
        Product product = catalogService.getProductBySku(sku);
        return ResponseEntity.ok(product);
    }
    
    @GetMapping("/products/{id}/images")
    public ResponseEntity<List<ProductImage>> getProductImages(@PathVariable Long id) {
        List<ProductImage> images = catalogService.getProductImages(id);
        return ResponseEntity.ok(images);
    }
    
    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getLocations() {
        List<Location> locations = inventoryService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
    
    @GetMapping("/inventory")
    public ResponseEntity<List<Inventory>> getInventory(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) String sku,
            @RequestParam(required = false) String locationCode) {
        List<Inventory> inventory = inventoryService.getInventory(productId, sku, locationCode);
        return ResponseEntity.ok(inventory);
    }
}