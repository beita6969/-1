package com.experiment.consumer2.controller;

import com.experiment.consumer2.client.ProductServiceClient;
import com.experiment.consumer2.client.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/aggregate")
public class AggregationController {
    
    @Autowired
    private UserServiceClient userServiceClient;
    
    @Autowired
    private ProductServiceClient productServiceClient;
    
    @GetMapping("/user-products/{userId}")
    public Map<String, Object> getUserWithProducts(@PathVariable Long userId) {
        Map<String, Object> result = new HashMap<>();
        result.put("source", "consumer-2");
        result.put("user", userServiceClient.getUserById(userId));
        result.put("products", productServiceClient.getAllProducts());
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
    
    @GetMapping("/all-data")
    public Map<String, Object> getAllData() {
        Map<String, Object> result = new HashMap<>();
        result.put("source", "consumer-2");
        result.put("users", userServiceClient.getAllUsers());
        result.put("products", productServiceClient.getAllProducts());
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
    
    @GetMapping("/product/{productId}/with-users")
    public Map<String, Object> getProductWithUsers(@PathVariable Long productId) {
        Map<String, Object> result = new HashMap<>();
        result.put("source", "consumer-2");
        result.put("product", productServiceClient.getProductById(productId));
        result.put("users", userServiceClient.getAllUsers());
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
    
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> result = new HashMap<>();
        result.put("status", "UP");
        result.put("service", "service-consumer-2");
        return result;
    }
}