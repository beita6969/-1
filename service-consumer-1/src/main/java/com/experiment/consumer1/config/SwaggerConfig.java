package com.experiment.consumer1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    
    @Value("${server.port}")
    private String serverPort;
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Service Consumer 1 API")
                        .version("1.0.0")
                        .description("服务消费者 1 - 调用用户服务的 API 文档")
                        .contact(new Contact()
                                .name("Consumer Team")
                                .email("consumer@example.com")))
                .servers(List.of(
                        new Server().url("http://localhost:" + serverPort).description("Local server")
                ));
    }
}