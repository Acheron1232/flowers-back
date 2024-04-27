package com.acheron.flowers.security.dto;

import com.acheron.flowers.security.entity.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Role role;
}
