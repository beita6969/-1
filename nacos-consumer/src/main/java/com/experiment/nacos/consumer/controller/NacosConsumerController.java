package com.experiment.nacos.consumer.controller;

import com.experiment.nacos.consumer.client.NacosProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
public class NacosConsumerController {
    
    @Autowired
    private NacosProviderClient nacosProviderClient;
    
    @Value("${consumer.retry.max-attempts:3}")
    private int maxRetryAttempts;
    
    @Value("${consumer.timeout:10000}")
    private int timeout;
    
    @Value("${server.port}")
    private String serverPort;
    
    @GetMapping("/nacos/consumer/hello/{name}")
    public Map<String, Object> callProvider(@PathVariable String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("consumerMessage", "This is Nacos Consumer calling provider");
        result.put("consumerPort", serverPort);
        result.put("maxRetryAttempts", maxRetryAttempts);
        result.put("timeout", timeout);
        result.put("providerResponse", nacosProviderClient.hello(name));
        return result;
    }
    
    @GetMapping("/nacos/consumer/provider-info")
    public Map<String, Object> getProviderInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("consumerService", "nacos-consumer");
        result.put("consumerPort", serverPort);
        result.put("providerInfo", nacosProviderClient.getServiceInfo());
        return result;
    }
    
    @GetMapping("/nacos/consumer/config")
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("maxRetryAttempts", maxRetryAttempts);
        config.put("timeout", timeout);
        config.put("serverPort", serverPort);
        return config;
    }
}