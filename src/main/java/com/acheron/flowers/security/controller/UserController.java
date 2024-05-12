package com.acheron.flowers.security.controller;

import com.acheron.flowers.security.dto.PasswordChangeDto;
import com.acheron.flowers.security.dto.UserChangeDto;

import com.acheron.flowers.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(Principal principal) {
        return userService.getCurrentUser(principal);
    }

    @PutMapping("/changeUser")
    public ResponseEntity<?> changeUser(Principal principal, @RequestBody UserChangeDto userChangeDto) {
        return userService.changeUser(principal,userChangeDto);
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<String> changePassword(Principal principal, @RequestBody PasswordChangeDto passwordChangeDto) {
       return userService.changePassword(principal,passwordChangeDto,authenticationManager);
    }



}
