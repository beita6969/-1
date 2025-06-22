package com.experiment.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 配置中心服务端
 * 
 * @EnableConfigServer: 启用Config Server功能
 * @EnableDiscoveryClient: 启用服务发现客户端
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
        System.out.println("=================================");
        System.out.println("Config Server started successfully!");
        System.out.println("Port: 8888");
        System.out.println("=================================");
    }
}