package com.acheron.flowers.security.mapper;

import com.acheron.flowers.security.dto.UserChangeDto;
import com.acheron.flowers.security.dto.UserGetDto;
import com.acheron.flowers.security.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserGetDto mapToUserGetDto(User user){
        return new UserGetDto(user.getFirstName(), user.getEmail(), user.getEmail(), user.getPhoneNumber(), user.getRole());
    }

    public User mapFromUserChangeDto(UserChangeDto userChangeDto,User user){
        return new User(user.getId(),
                userChangeDto.getFirstName()!= null? userChangeDto.getFirstName() : user.getFirstName(),
                userChangeDto.getLastName()!= null? userChangeDto.getLastName() : user.getLastName(),
                userChangeDto.getEmail()!= null? userChangeDto.getEmail() : user.getEmail(),
                user.getPassword(),
                userChangeDto.getPhoneNumber()!= null? userChangeDto.getPhoneNumber() : user.getPhoneNumber(),
                user.getRole()
                );
    }
}
