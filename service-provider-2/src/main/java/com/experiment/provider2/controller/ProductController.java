package com.experiment.provider2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/products")
@Tag(name = "产品管理", description = "产品管理相关的 API 接口")
public class ProductController {
    
    private final Map<Long, Product> products = new HashMap<>();
    
    public ProductController() {
        products.put(1L, new Product(1L, "Laptop", "High-performance laptop", 999.99));
        products.put(2L, new Product(2L, "Smartphone", "Latest model smartphone", 699.99));
        products.put(3L, new Product(3L, "Headphones", "Wireless noise-cancelling headphones", 299.99));
        products.put(4L, new Product(4L, "Monitor", "4K Ultra HD Monitor", 499.99));
    }
    
    @GetMapping
    @Operation(summary = "获取所有产品", description = "返回系统中所有产品的列表")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取产品列表",
                content = @Content(schema = @Schema(implementation = Product.class)))
    })
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取产品", description = "根据产品ID获取产品详细信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取产品信息",
                content = @Content(schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "404", description = "产品不存在")
    })
    public Product getProductById(
            @Parameter(description = "产品ID", required = true)
            @PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return product;
    }
    
    @Schema(description = "产品实体")
    static class Product {
        @Schema(description = "产品ID", example = "1")
        private Long id;
        
        @Schema(description = "产品名称", example = "笔记本电脑")
        private String name;
        
        @Schema(description = "产品描述", example = "高性能笔记本电脑")
        private String description;
        
        @Schema(description = "产品价格", example = "5999.99")
        private Double price;
        
        public Product(Long id, String name, String description, Double price) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
        }
        
        // Getters and setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }
    }
    
    @ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
    static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }
}
