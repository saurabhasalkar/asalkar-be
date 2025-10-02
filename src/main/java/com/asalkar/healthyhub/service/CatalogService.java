package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.entity.catalog.Brand;
import com.asalkar.healthyhub.entity.catalog.Category;
import com.asalkar.healthyhub.entity.catalog.Product;
import com.asalkar.healthyhub.entity.catalog.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    
    public List<Category> getAllCategories() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public List<Brand> getAllBrands() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Page<Product> searchProducts(String q, Long category, Long brand, Boolean isActive, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Product getProductById(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Product getProductBySku(String sku) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public List<ProductImage> getProductImages(Long productId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Category createCategory(Category category) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Category updateCategory(Long id, Category category) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public void deleteCategory(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Brand createBrand(Brand brand) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Brand updateBrand(Long id, Brand brand) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public void deleteBrand(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Product createProduct(Product product) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Product updateProduct(Long id, Product product) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public void softDeleteProduct(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public ProductImage addProductImage(Long productId, String url, String altText) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public void deleteProductImage(Long imageId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}