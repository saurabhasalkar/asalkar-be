package com.asalkar.healthyhub.entity.catalog;

import com.asalkar.healthyhub.entity.common.BaseEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    
    @Column(name = "sku", length = 64, unique = true, nullable = false)
    private String sku;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;
    
    @Column(name = "name", length = 200, nullable = false)
    private String name;
    
    @Column(name = "slug", length = 200, unique = true, nullable = false)
    private String slug;
    
    @Column(name = "description_html", columnDefinition = "TEXT")
    private String descriptionHtml;
    
    @Column(name = "unit_measure", length = 16)
    private String unitMeasure;
    
    @Column(name = "unit_size", precision = 10, scale = 2)
    private BigDecimal unitSize;
    
    @Column(name = "price_mrp", precision = 10, scale = 2, nullable = false)
    private BigDecimal priceMrp;
    
    @Column(name = "price_sale", precision = 10, scale = 2)
    private BigDecimal priceSale;
    
    @Column(name = "tax_rate_percent", precision = 5, scale = 2)
    private BigDecimal taxRatePercent = BigDecimal.ZERO;
    
    public Product() {
        this.taxRatePercent = BigDecimal.ZERO;
        this.isActive = true;
    }
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> images;
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    
    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public String getDescriptionHtml() { return descriptionHtml; }
    public void setDescriptionHtml(String descriptionHtml) { this.descriptionHtml = descriptionHtml; }
    
    public String getUnitMeasure() { return unitMeasure; }
    public void setUnitMeasure(String unitMeasure) { this.unitMeasure = unitMeasure; }
    
    public BigDecimal getUnitSize() { return unitSize; }
    public void setUnitSize(BigDecimal unitSize) { this.unitSize = unitSize; }
    
    public BigDecimal getPriceMrp() { return priceMrp; }
    public void setPriceMrp(BigDecimal priceMrp) { this.priceMrp = priceMrp; }
    
    public BigDecimal getPriceSale() { return priceSale; }
    public void setPriceSale(BigDecimal priceSale) { this.priceSale = priceSale; }
    
    public BigDecimal getTaxRatePercent() { return taxRatePercent; }
    public void setTaxRatePercent(BigDecimal taxRatePercent) { this.taxRatePercent = taxRatePercent; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public OffsetDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(OffsetDateTime deletedAt) { this.deletedAt = deletedAt; }
    
    public List<ProductImage> getImages() { return images; }
    public void setImages(List<ProductImage> images) { this.images = images; }
}