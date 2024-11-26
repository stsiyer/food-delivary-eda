// OrderEvent.java
package com.example.orderprocessor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderItem {
    @JsonProperty("item_id")
    private Long itemId;
    
    @JsonProperty("item_name")
    private String itemName;
    
    private Integer quantity;
}