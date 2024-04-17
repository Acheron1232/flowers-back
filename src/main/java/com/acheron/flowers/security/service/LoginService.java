package com.acheron.flowers.security.service;

import com.acheron.flowers.security.dto.LoginRequest;
import com.acheron.flowers.security.dto.RegistrationRequest;
import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.exception.UserAlreadyExistsException;
import com.acheron.flowers.security.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<String> registration(RegistrationRequest registrationRequest){
        try {
            User save = userService.save(registrationRequest);
            return ResponseEntity.ok(jwtUtil.generateToken(save));
        }
        catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("User already exists");
        }
    }
    public Cookie login(LoginRequest request) throws BadRequestException {
        if (request.getEmail() != null && request.getPassword() != null) {
            try {
                authenticationManager
                        .authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        request.getEmail(),
                                        request.getPassword()));
            } catch (Exception e) {
                throw new BadCredentialsException("Bad credentials");
            }
        }else {
            throw new BadRequestException("Bad request");
        }
        return createCookie(jwtUtil.generateToken(userService.findByEmail(request.getEmail()).orElseThrow()));
    }

    private Cookie createCookie(String jwt){
        Cookie cookie = new Cookie("accessToken", jwt);
        cookie.setMaxAge(200000);
//        cookie.setSecure(false);
        cookie.setSecure(true); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
