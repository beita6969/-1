package com.experiment.provider1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置中心测试控制器
 * @RefreshScope 注解使得配置可以动态刷新
 */
@RestController
@RequestMapping("/config")
@RefreshScope
@Tag(name = "配置管理", description = "配置中心相关的测试接口")
public class ConfigTestController {
    
    // 从配置中心读取配置
    @Value("${business.service.name:默认服务名}")
    private String serviceName;
    
    @Value("${business.service.description:默认描述}")
    private String serviceDescription;
    
    @Value("${business.service.version:v1.0}")
    private String serviceVersion;
    
    @Value("${environment:default}")
    private String environment;
    
    @Value("${user.service.default-page-size:10}")
    private int defaultPageSize;
    
    @Value("${user.service.cache-enabled:false}")
    private boolean cacheEnabled;
    
    /**
     * 获取当前配置信息
     */
    @GetMapping("/info")
    @Operation(summary = "获取配置信息", description = "获取当前服务的所有配置信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功返回配置信息")
    })
    public Map<String, Object> getConfigInfo() {
        Map<String, Object> configInfo = new HashMap<>();
        configInfo.put("serviceName", serviceName);
        configInfo.put("serviceDescription", serviceDescription);
        configInfo.put("serviceVersion", serviceVersion);
        configInfo.put("environment", environment);
        configInfo.put("defaultPageSize", defaultPageSize);
        configInfo.put("cacheEnabled", cacheEnabled);
        configInfo.put("timestamp", System.currentTimeMillis());
        return configInfo;
    }
    
    /**
     * 测试配置刷新
     * 需要POST请求到 /actuator/refresh 端点来刷新配置
     */
    @GetMapping("/refresh-hint")
    @Operation(summary = "配置刷新提示", description = "获取如何刷新配置的说明")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "返回配置刷新的操作说明")
    })
    public Map<String, String> getRefreshHint() {
        Map<String, String> hint = new HashMap<>();
        hint.put("message", "要刷新配置，请执行: curl -X POST http://localhost:8081/actuator/refresh");
        hint.put("currentEnvironment", environment);
        hint.put("note", "修改配置文件后，执行上述命令即可动态更新配置");
        return hint;
    }
}