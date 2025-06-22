package com.experiment.consumer2.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "service-provider-2")
public interface ProductServiceClient {
    
    @GetMapping("/products/{id}")
    Map<String, Object> getProductById(@PathVariable("id") Long id);
    
    @GetMapping("/products")
    List<Map<String, Object>> getAllProducts();
}