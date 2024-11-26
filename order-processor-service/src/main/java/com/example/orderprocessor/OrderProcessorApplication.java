package com.example.orderprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class OrderProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderProcessorApplication.class, args);
    }
}