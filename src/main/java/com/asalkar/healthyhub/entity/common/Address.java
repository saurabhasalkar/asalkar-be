package com.asalkar.healthyhub.entity.common;

import com.asalkar.healthyhub.entity.auth.User;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "name", length = 80)
    private String name;
    
    @Column(name = "line1", length = 200, nullable = false)
    private String line1;
    
    @Column(name = "line2", length = 200)
    private String line2;
    
    @Column(name = "city", length = 120, nullable = false)
    private String city;
    
    @Column(name = "state", length = 120, nullable = false)
    private String state;
    
    @Column(name = "postal_code", length = 20, nullable = false)
    private String postalCode;
    
    @Column(name = "country", length = 80)
    private String country = "India";
    
    public Address() {
        this.country = "India";
    }
    
    @Column(name = "phone", length = 20)
    private String phone;
    
    @Column(name = "is_default")
    private Boolean isDefault = false;
    
    // Getters and Setters
    public Long getAddressId() { return addressId; }
    public void setAddressId(Long addressId) { this.addressId = addressId; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLine1() { return line1; }
    public void setLine1(String line1) { this.line1 = line1; }
    
    public String getLine2() { return line2; }
    public void setLine2(String line2) { this.line2 = line2; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public Boolean getIsDefault() { return isDefault; }
    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }
}