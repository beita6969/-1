package com.experiment.consumer1.controller;

import com.experiment.consumer1.client.UserServiceClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
@Tag(name = "服务消费者", description = "通过 Feign 调用其他服务的 API")
public class ConsumerController {
    
    @Autowired
    private UserServiceClient userServiceClient;
    
    @GetMapping("/users/{id}")
    @Operation(summary = "获取用户信息", description = "通过 Feign 客户端调用用户服务获取指定用户信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取用户信息"),
        @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    public Map<String, Object> getUserInfo(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        result.put("source", "consumer-1");
        result.put("userData", userServiceClient.getUserById(id));
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
    
    @GetMapping("/users")
    @Operation(summary = "获取所有用户", description = "通过 Feign 客户端调用用户服务获取所有用户列表")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取用户列表")
    })
    public Map<String, Object> getAllUsers() {
        Map<String, Object> result = new HashMap<>();
        result.put("source", "consumer-1");
        result.put("users", userServiceClient.getAllUsers());
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
    
    @GetMapping("/health")
    @Operation(summary = "健康检查", description = "检查服务消费者的健康状态")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "服务正常")
    })
    public Map<String, String> health() {
        Map<String, String> result = new HashMap<>();
        result.put("status", "UP");
        result.put("service", "service-consumer-1");
        return result;
    }
}
