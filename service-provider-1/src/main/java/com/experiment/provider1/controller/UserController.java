package com.experiment.provider1.controller;

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
@RequestMapping("/users")
@Tag(name = "用户管理", description = "用户管理相关的 API 接口")
public class UserController {
    
    private final Map<Long, User> users = new HashMap<>();
    
    public UserController() {
        // Initialize with some sample data
        users.put(1L, new User(1L, "John Doe", "john.doe@example.com"));
        users.put(2L, new User(2L, "Jane Smith", "jane.smith@example.com"));
        users.put(3L, new User(3L, "Bob Johnson", "bob.johnson@example.com"));
    }
    
    @GetMapping
    @Operation(summary = "获取所有用户", description = "返回系统中所有用户的列表")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取用户列表",
                content = @Content(schema = @Schema(implementation = User.class)))
    })
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取用户", description = "根据用户ID获取用户详细信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取用户信息",
                content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    public User getUserById(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        User user = users.get(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return user;
    }
    
    // Inner class for User model
    @Schema(description = "用户实体")
    static class User {
        @Schema(description = "用户ID", example = "1")
        private Long id;
        
        @Schema(description = "用户姓名", example = "张三")
        private String name;
        
        @Schema(description = "用户邮箱", example = "zhangsan@example.com")
        private String email;
        
        public User(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
        
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
    }
    
    @ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
    static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}