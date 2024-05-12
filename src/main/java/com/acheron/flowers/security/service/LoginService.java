package com.acheron.flowers.security.service;

import com.acheron.flowers.security.dto.LoginRequest;
import com.acheron.flowers.security.dto.RegistrationRequest;
import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.exception.custom.EmailAlreadyExists;
import com.acheron.flowers.security.exception.custom.PhoneNumberAlreadyExists;
import com.acheron.flowers.security.exception.custom.UserNotFoundException;
import com.acheron.flowers.security.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public Cookie registration(RegistrationRequest registrationRequest) {
        if(userService.findByEmail(registrationRequest.getEmail()).isEmpty()){
            if(userService.findByPhoneNumber(registrationRequest.getPhoneNumber()).isEmpty()){

                User user = userService.save(registrationRequest);
                return createCookie(jwtUtil.generateToken(user));
            } throw new PhoneNumberAlreadyExists("User with phone number: "+ registrationRequest.getPhoneNumber() + " is already registered");
        }else throw new EmailAlreadyExists("User with email: "+ registrationRequest.getEmail() + " is already registered");
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
        } else {
            throw new BadRequestException("Bad request");
        }
        return createCookie(jwtUtil.generateToken(userService.findByEmail(request.getEmail()).orElseThrow()));
    }

    private Cookie createCookie(String jwt) {
        Cookie cookie = new Cookie("accessToken", jwt);
        cookie.setMaxAge(200000);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

    public String findByEmail(String email) {
        userService.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found"));
        return email;
    }

    public Cookie logout(HttpServletRequest request) {
        Cookie cookie = Arrays.stream(request.getCookies()).filter(cookie1 -> cookie1.getName().equals("accessToken")).findFirst().orElse(null);
        if (cookie != null) {
            cookie.setValue("null");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            return cookie;
        }
        return null;
    }
}
