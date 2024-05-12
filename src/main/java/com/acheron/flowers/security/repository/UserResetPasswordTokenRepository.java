package com.acheron.flowers.security.repository;

import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.entity.UserResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserResetPasswordTokenRepository extends JpaRepository<UserResetPasswordToken,Long> {


    Optional<UserResetPasswordToken> findByToken(String token);
}
