package com.acheron.flowers.security.controller;

import com.acheron.flowers.security.dto.PasswordChangeDto;
import com.acheron.flowers.security.dto.UserChangeDto;
import com.acheron.flowers.security.dto.UserGetDto;
import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.mapper.UserMapper;
import com.acheron.flowers.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(userMapper.mapToUserGetDto(user));
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }

    }

    @PostMapping("/changeUser")
    public ResponseEntity<String> changeUser(Principal principal, @RequestBody UserChangeDto userChangeDto) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        if (user != null) {
            userMapper.mapFromUserChangeDto(userChangeDto, user);
            return ResponseEntity.ok("Successful");
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(Principal principal, @RequestBody PasswordChangeDto passwordChangeDto) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        if (user != null && passwordChangeDto.getCurrentPassword() != null) {
            try {
                authenticationManager
                        .authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        user.getEmail(),
                                        passwordChangeDto.getCurrentPassword()));
            } catch (Exception e) {
                throw new BadCredentialsException("Bad credentials");
            }
            if (passwordChangeDto.getNewPassword() != null){
                user.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
                userService.save(user);
                return ResponseEntity.ok("Successful");
            }else {
                return ResponseEntity.badRequest().body("Bad request");
            }
        }
        return ResponseEntity.badRequest().body("Bad request");
    }

}
