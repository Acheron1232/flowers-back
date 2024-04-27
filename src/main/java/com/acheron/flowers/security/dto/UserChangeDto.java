package com.acheron.flowers.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangeDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
