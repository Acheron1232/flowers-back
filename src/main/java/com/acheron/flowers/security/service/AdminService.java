package com.acheron.flowers.security.service;

import com.acheron.flowers.security.dto.UserChangeAdminDto;
import com.acheron.flowers.security.dto.UserChangeDto;
import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.exception.custom.UserNotFoundException;
import com.acheron.flowers.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;
    private final UserRepository userRepository;

    public List<User> findAll(Integer pageId,Integer pageSize) {
        return userRepository.findAll(
                PageRequest.of(pageId, pageSize)
        ).toList();
    }

    public Optional<User> findById(Long id) {
        return userService.findById(id);
    }

    public ResponseEntity<?> changeUser(UserChangeAdminDto userDto, Long id){
        try{
            User user = userService.findById(id).orElseThrow(UserNotFoundException::new);
            User newUser = userService.save(new User(
                    user.getId(),
                    userDto.getFirstName()!=null? userDto.getFirstName() : user.getFirstName(),
                    userDto.getLastName()!=null? userDto.getLastName() : user.getLastName(),
                    userDto.getEmail()!=null? userDto.getEmail() : user.getEmail(),
                    userDto.getPassword()!=null? userDto.getPassword() : user.getPassword(),
                    userDto.getPhoneNumber()!=null? userDto.getPhoneNumber() : user.getPhoneNumber(),
                    userDto.getRole()!=null? userDto.getRole() : user.getRole()
            ),false);
            return ResponseEntity.ok(newUser);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<String> delete(Long id){
        return userService.delete(id) ? ResponseEntity.ok("Success") : ResponseEntity.badRequest().body("Error deleting");
    }
}
