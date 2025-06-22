package com.experiment.nacos.provider.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@Tag(name = "Nacos Provider API", description = "Nacos 服务提供者接口")
public class NacosProviderController {
    
    @Value("${server.port}")
    private String serverPort;
    
    @Value("${message.welcome:Hello from Default}")
    private String welcomeMessage;
    
    @Value("${message.version:1.0.0}")
    private String version;
    
    @Value("${message.environment:default}")
    private String environment;
    
    @GetMapping("/nacos/hello/{name}")
    @Operation(summary = "Hello 接口", description = "返回带有配置信息的问候消息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功返回问候信息")
    })
    public Map<String, Object> hello(
            @Parameter(description = "名称", required = true, example = "World")
            @PathVariable String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", welcomeMessage + " " + name);
        result.put("serverPort", serverPort);
        result.put("version", version);
        result.put("environment", environment);
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
    
    @GetMapping("/nacos/service-info")
    @Operation(summary = "获取服务信息", description = "返回服务的详细信息和配置")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功返回服务信息")
    })
    public Map<String, Object> getServiceInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("serviceName", "nacos-provider");
        info.put("serverPort", serverPort);
        info.put("version", version);
        info.put("environment", environment);
        info.put("welcomeMessage", welcomeMessage);
        info.put("description", "This is a Nacos service provider with dynamic configuration");
        return info;
    }
    
    @GetMapping("/nacos/config")
    @Operation(summary = "获取配置信息", description = "返回当前生效的动态配置")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功返回配置信息")
    })
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("welcomeMessage", welcomeMessage);
        config.put("version", version);
        config.put("environment", environment);
        config.put("serverPort", serverPort);
        return config;
    }
}