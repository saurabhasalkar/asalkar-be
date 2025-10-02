package com.asalkar.healthyhub.service;

import com.asalkar.healthyhub.entity.commerce.Inventory;
import com.asalkar.healthyhub.entity.commerce.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    
    public List<Location> getAllLocations() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public List<Inventory> getInventory(Long productId, String sku, String locationCode) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Location createLocation(Location location) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Location updateLocation(Long locationId, Location location) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Inventory adjustInventory(Long productId, String sku, Long locationId, String code, Integer delta, String note) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}