package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.entity.catalog.Brand;
import com.asalkar.healthyhub.entity.catalog.Category;
import com.asalkar.healthyhub.entity.catalog.Product;
import com.asalkar.healthyhub.entity.catalog.ProductImage;
import com.asalkar.healthyhub.repository.BrandRepository;
import com.asalkar.healthyhub.repository.CategoryRepository;
import com.asalkar.healthyhub.repository.ProductImageRepository;
import com.asalkar.healthyhub.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CatalogService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private BrandRepository brandRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductImageRepository productImageRepository;
    
    public List<Category> getAllCategories() {
        return categoryRepository.findByIsActiveTrueOrderByName();
    }
    
    public List<Brand> getAllBrands() {
        return brandRepository.findAllByOrderByName();
    }
    
    public Page<Product> searchProducts(String q, Long category, Long brand, Boolean isActive, Pageable pageable) {
        if (isActive != null && isActive) {
            return productRepository.findByIsActiveTrue(pageable);
        }
        return productRepository.findAll(pageable);
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    
    public Product getProductBySku(String sku) {
        return productRepository.findBySku(sku)
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    
    public List<ProductImage> getProductImages(Long productId) {
        return productImageRepository.findByProductProductIdOrderBySortOrder(productId);
    }
    
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    public Category updateCategory(Long id, Category category) {
        Category existing = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        if (category.getName() != null) existing.setName(category.getName());
        if (category.getDescription() != null) existing.setDescription(category.getDescription());
        return categoryRepository.save(existing);
    }
    
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }
    
    public Brand updateBrand(Long id, Brand brand) {
        Brand existing = brandRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Brand not found"));
        if (brand.getName() != null) existing.setName(brand.getName());
        if (brand.getDescription() != null) existing.setDescription(brand.getDescription());
        return brandRepository.save(existing);
    }
    
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getName() != null) existing.setName(product.getName());
        if (product.getPriceMrp() != null) existing.setPriceMrp(product.getPriceMrp());
        if (product.getPriceSale() != null) existing.setPriceSale(product.getPriceSale());
        return productRepository.save(existing);
    }
    
    public void softDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDeletedAt(OffsetDateTime.now());
        productRepository.save(product);
    }
    
    public ProductImage addProductImage(Long productId, String url, String altText) {
        Product product = getProductById(productId);
        ProductImage image = new ProductImage();
        image.setProduct(product);
        image.setUrl(url);
        image.setAltText(altText);
        return productImageRepository.save(image);
    }
    
    public void deleteProductImage(Long imageId) {
        productImageRepository.deleteById(imageId);
    }
}