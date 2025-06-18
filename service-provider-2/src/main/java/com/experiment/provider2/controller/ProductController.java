package com.experiment.provider2.controller;

import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final Map<Long, Product> products = new HashMap<>();
    
    public ProductController() {
        // Initialize with some sample data
        products.put(1L, new Product(1L, "Laptop", "High-performance laptop", new BigDecimal("999.99")));
        products.put(2L, new Product(2L, "Smartphone", "Latest model smartphone", new BigDecimal("699.99")));
        products.put(3L, new Product(3L, "Headphones", "Wireless noise-cancelling headphones", new BigDecimal("299.99")));
        products.put(4L, new Product(4L, "Monitor", "4K Ultra HD Monitor", new BigDecimal("499.99")));
    }
    
    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return product;
    }
    
    // Inner class for Product model
    static class Product {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        
        public Product(Long id, String name, String description, BigDecimal price) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
        }
        
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getDescription() {
            return description;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
        
        public BigDecimal getPrice() {
            return price;
        }
        
        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
    
    @ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
    static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }
}