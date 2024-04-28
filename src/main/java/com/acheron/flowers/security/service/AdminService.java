package com.acheron.flowers.security.service;

import com.acheron.flowers.security.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;

    public List<User> findAll() {
        return userService.findAll();
    }
}
