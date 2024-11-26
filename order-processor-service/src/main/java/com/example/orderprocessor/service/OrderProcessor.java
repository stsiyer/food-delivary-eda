// OrderProcessor.java
package com.example.orderprocessor.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.orderprocessor.model.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderProcessor {
    private final LocationService locationService;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    
    public OrderProcessor(LocationService locationService, 
                         KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.locationService = locationService;
        this.kafkaTemplate = kafkaTemplate;
    }
    
    @KafkaListener(topics = "${application.kafka.order-topic}", 
                  groupId = "${application.kafka.group-id}")
    public void processOrder(OrderEvent orderEvent) {
        try {
            log.info("Received order event: {}", orderEvent);
            
            // Determine delivery partner based on address
            String deliveryPartnerQueue = locationService.determineDeliveryPartner(
                orderEvent.getDeliveryAddress()
            );
            
            // Publish to delivery partner queue
            kafkaTemplate.send(deliveryPartnerQueue, orderEvent)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Successfully published order {} to delivery partner queue {}", 
                            orderEvent.getOrderId(), deliveryPartnerQueue);
                    } else {
                        log.error("Failed to publish order {} to delivery partner queue {}", 
                            orderEvent.getOrderId(), deliveryPartnerQueue, ex);
                    }
                });
            
        } catch (Exception e) {
            log.error("Error processing order event: {}", orderEvent, e);
        }
    }
}