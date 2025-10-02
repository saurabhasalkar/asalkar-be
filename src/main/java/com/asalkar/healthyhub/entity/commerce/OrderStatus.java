package com.asalkar.healthyhub.entity.commerce;

import jakarta.persistence.*;

@Entity
@Table(name = "order_status")
public class OrderStatus {
    
    @Id
    @Column(name = "status_id")
    private Short statusId;
    
    @Column(name = "code", length = 30, unique = true, nullable = false)
    private String code;
    
    @Column(name = "sort_order")
    private Integer sortOrder = 0;
    
    public Short getStatusId() { return statusId; }
    public void setStatusId(Short statusId) { this.statusId = statusId; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}