package com.acheron.flowers.security.controller;

import com.acheron.flowers.security.dto.LoginRequest;
import com.acheron.flowers.security.dto.RegistrationRequest;
import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.exception.UserAlreadyExistsException;
import com.acheron.flowers.security.jwt.JwtUtil;
import com.acheron.flowers.security.service.LoginService;
import com.acheron.flowers.security.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class LoginController {
//    private final JwtUtil jwtUtil;
//    private final AuthenticationManager authenticationManager;
//    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody(required = false) RegistrationRequest registrationRequest) {
//        try {
//            User save = userService.save(registrationRequest);
//            return ResponseEntity.ok(jwtUtil.generateToken(save));
//        }
//        catch (UserAlreadyExistsException e) {
//            return ResponseEntity.badRequest().body("User already exists");
//        }
        return loginService.registration(registrationRequest);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpServletResponse response) {
//        if (request.getEmail() != null) {
//            try {
//                authenticationManager
//                        .authenticate(
//                                new UsernamePasswordAuthenticationToken(
//                                        request.getEmail(),
//                                        request.getPassword()));
//            } catch (BadCredentialsException e) {
//                return ResponseEntity.status(401).body("Bad credentials");
//
//            }
//        }
//        return ResponseEntity.ok(jwtUtil.generateToken(userService.findByEmail(request.getEmail()).orElseThrow()));
        try {
            response.addCookie(loginService.login(request));
        } catch (BadRequestException e) {
            ResponseEntity.badRequest().body("Bad request");
        }catch (BadCredentialsException e){
            ResponseEntity.badRequest().body("Bad credentials");
        }
        return ResponseEntity.ok("success");
    }
}
