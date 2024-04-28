package com.acheron.flowers.security.controller;

import com.acheron.flowers.security.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/admin")
public class AdminController {
    private final AdminService adminService;

}
