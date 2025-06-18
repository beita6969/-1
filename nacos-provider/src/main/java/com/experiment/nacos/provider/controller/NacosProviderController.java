package com.experiment.nacos.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NacosProviderController {
    
    @Value("${server.port}")
    private String serverPort;
    
    @GetMapping("/nacos/hello/{name}")
    public Map<String, Object> hello(@PathVariable String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Hello " + name + " from Nacos Provider");
        result.put("serverPort", serverPort);
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
    
    @GetMapping("/nacos/service-info")
    public Map<String, Object> getServiceInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("serviceName", "nacos-provider");
        info.put("serverPort", serverPort);
        info.put("version", "1.0.0");
        info.put("description", "This is a Nacos service provider");
        return info;
    }
}