package com.asalkar.healthyhub.entity.catalog;

import com.asalkar.healthyhub.entity.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "brand")
public class Brand extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;
    
    @Column(name = "name", length = 120, unique = true, nullable = false)
    private String name;
    
    @Column(name = "slug", length = 140, unique = true, nullable = false)
    private String slug;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    public Long getBrandId() { return brandId; }
    public void setBrandId(Long brandId) { this.brandId = brandId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}