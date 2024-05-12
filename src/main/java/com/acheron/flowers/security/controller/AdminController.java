package com.acheron.flowers.security.controller;

import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public List<User> findAll(@RequestParam(required = false) Integer pageId, @RequestParam(required = false) Integer pageSize) {
        return adminService.findAll(pageId, pageSize);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.of(adminService.findById(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return adminService.delete(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> changeUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return adminService.changeUser(user);
    }
}
