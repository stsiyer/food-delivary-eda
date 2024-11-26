package com.example.orderprocessor.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "application")
@Data
public class Config {
    private Kafka kafka;
    private List<String> deliveryPartners;
    
    @Data
    public static class Kafka {
        private String bootstrapServers;
        private String orderTopic;
        private String groupId;
        private String autoOffsetReset;
    }
}

