// LocationService.java
package com.example.orderprocessor.service;

import com.example.orderprocessor.config.Config;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class LocationService {
    private final List<String> deliveryPartners;
    private final Random random;
    
    public LocationService(Config config) {
        this.deliveryPartners = config.getDeliveryPartners();
        this.random = new Random();
    }
    
    public String determineDeliveryPartner(String address) {
        // In a real scenario, you would implement logic to determine the best delivery partner
        // based on the address. For this example, we're randomly selecting one.
        return deliveryPartners.get(random.nextInt(deliveryPartners.size()));
    }
}

