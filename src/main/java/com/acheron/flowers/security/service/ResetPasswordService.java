package com.acheron.flowers.security.service;

import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.entity.UserResetPasswordToken;
import com.acheron.flowers.security.repository.UserResetPasswordTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {
    private final UserResetPasswordTokenRepository userResetPasswordTokenRepository;
    private final UserService userService;

    public void updateResetPasswordToken(String token, String email) throws RuntimeException {//!!!!!!!!!!
        User user = userService.findByEmail(email).orElse(null);
        if (user != null) {
            userResetPasswordTokenRepository.save(new UserResetPasswordToken(null,user,token));
        } else {
            throw new RuntimeException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userResetPasswordTokenRepository.findByToken(token).orElseThrow().getUser();
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userService.save(user);
    }
}
