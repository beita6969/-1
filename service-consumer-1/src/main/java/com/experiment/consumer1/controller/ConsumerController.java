package com.experiment.consumer1.controller;

import com.experiment.consumer1.client.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    
    @Autowired
    private UserServiceClient userServiceClient;
    
    @GetMapping("/users/{id}")
    public Map<String, Object> getUserInfo(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        result.put("source", "consumer-1");
        result.put("userData", userServiceClient.getUserById(id));
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
    
    @GetMapping("/users")
    public Map<String, Object> getAllUsers() {
        Map<String, Object> result = new HashMap<>();
        result.put("source", "consumer-1");
        result.put("users", userServiceClient.getAllUsers());
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
    
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> result = new HashMap<>();
        result.put("status", "UP");
        result.put("service", "service-consumer-1");
        return result;
    }
}