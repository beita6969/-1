package com.experiment.nacos.consumer.controller;

import com.experiment.nacos.consumer.client.NacosProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NacosConsumerController {
    
    @Autowired
    private NacosProviderClient nacosProviderClient;
    
    @GetMapping("/nacos/consumer/hello/{name}")
    public Map<String, Object> callProvider(@PathVariable String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("consumerMessage", "This is Nacos Consumer calling provider");
        result.put("providerResponse", nacosProviderClient.hello(name));
        return result;
    }
    
    @GetMapping("/nacos/consumer/provider-info")
    public Map<String, Object> getProviderInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("consumerService", "nacos-consumer");
        result.put("providerInfo", nacosProviderClient.getServiceInfo());
        return result;
    }
}