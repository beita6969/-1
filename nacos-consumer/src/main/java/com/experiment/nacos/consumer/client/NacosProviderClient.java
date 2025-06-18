package com.experiment.nacos.consumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "nacos-provider")
public interface NacosProviderClient {
    
    @GetMapping("/nacos/hello/{name}")
    Map<String, Object> hello(@PathVariable("name") String name);
    
    @GetMapping("/nacos/service-info")
    Map<String, Object> getServiceInfo();
}