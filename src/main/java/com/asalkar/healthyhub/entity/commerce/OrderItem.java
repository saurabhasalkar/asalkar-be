package com.asalkar.healthyhub.entity.commerce;

import com.asalkar.healthyhub.entity.catalog.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    
    @Column(name = "product_name_snapshot", length = 200, nullable = false)
    private String productNameSnapshot;
    
    @Column(name = "sku_snapshot", length = 64, nullable = false)
    private String skuSnapshot;
    
    @Column(name = "unit_price_snapshot", precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPriceSnapshot;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "tax_rate_percent_snapshot", precision = 5, scale = 2)
    private BigDecimal taxRatePercentSnapshot = BigDecimal.ZERO;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    
    public Long getOrderItemId() { return orderItemId; }
    public void setOrderItemId(Long orderItemId) { this.orderItemId = orderItemId; }
    
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public String getProductNameSnapshot() { return productNameSnapshot; }
    public void setProductNameSnapshot(String productNameSnapshot) { this.productNameSnapshot = productNameSnapshot; }
    
    public String getSkuSnapshot() { return skuSnapshot; }
    public void setSkuSnapshot(String skuSnapshot) { this.skuSnapshot = skuSnapshot; }
    
    public BigDecimal getUnitPriceSnapshot() { return unitPriceSnapshot; }
    public void setUnitPriceSnapshot(BigDecimal unitPriceSnapshot) { this.unitPriceSnapshot = unitPriceSnapshot; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public BigDecimal getTaxRatePercentSnapshot() { return taxRatePercentSnapshot; }
    public void setTaxRatePercentSnapshot(BigDecimal taxRatePercentSnapshot) { this.taxRatePercentSnapshot = taxRatePercentSnapshot; }
    
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
}