package com.example.orderprocessor.model;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderEvent {
    @JsonProperty("order_id")
    private Long orderId;
    
    @JsonProperty("customer_name")
    private String customerName;
    
    private List<OrderItem> items;
    
    @JsonProperty("total_amount")
    private Double totalAmount;
    
    @JsonProperty("delivery_address")
    private String deliveryAddress;
    
    @JsonProperty("order_timestamp")
    private Instant orderTimestamp;
}