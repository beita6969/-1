package com.experiment.consumer2.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "service-provider-1")
public interface UserServiceClient {
    
    @GetMapping("/users/{id}")
    Map<String, Object> getUserById(@PathVariable("id") Long id);
    
    @GetMapping("/users")
    Map<String, Object> getAllUsers();
}