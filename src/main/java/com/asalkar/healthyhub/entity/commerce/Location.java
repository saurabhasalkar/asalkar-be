package com.asalkar.healthyhub.entity.commerce;

import com.asalkar.healthyhub.entity.common.Address;
import com.asalkar.healthyhub.entity.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;
    
    @Column(name = "code", length = 40, unique = true, nullable = false)
    private String code;
    
    @Column(name = "name", length = 120, nullable = false)
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}