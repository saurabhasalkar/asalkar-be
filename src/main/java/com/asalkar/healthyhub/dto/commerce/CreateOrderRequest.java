package com.asalkar.healthyhub.dto.commerce;

import jakarta.validation.constraints.NotNull;

public class CreateOrderRequest {
    @NotNull
    private Long shippingAddressId;
    
    private Long billingAddressId;
    
    public Long getShippingAddressId() { return shippingAddressId; }
    public void setShippingAddressId(Long shippingAddressId) { this.shippingAddressId = shippingAddressId; }
    
    public Long getBillingAddressId() { return billingAddressId; }
    public void setBillingAddressId(Long billingAddressId) { this.billingAddressId = billingAddressId; }
}