package com.experiment.provider1.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final Map<Long, User> users = new HashMap<>();
    
    public UserController() {
        // Initialize with some sample data
        users.put(1L, new User(1L, "John Doe", "john.doe@example.com"));
        users.put(2L, new User(2L, "Jane Smith", "jane.smith@example.com"));
        users.put(3L, new User(3L, "Bob Johnson", "bob.johnson@example.com"));
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = users.get(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return user;
    }
    
    // Inner class for User model
    static class User {
        private Long id;
        private String name;
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