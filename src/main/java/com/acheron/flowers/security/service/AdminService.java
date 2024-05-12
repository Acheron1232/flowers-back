package com.acheron.flowers.security.service;

import com.acheron.flowers.security.entity.User;
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
@Slf4j
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

    public ResponseEntity<?> changeUser(User user){
        try{
            User newUser = userService.save(user);
            return ResponseEntity.ok(newUser);
        }catch (Exception e){
            log.debug(e.getMessage());
            return ResponseEntity.badRequest().body("Error changing");
        }
    }

    public ResponseEntity<String> delete(Long id){
        return userService.delete(id) ? ResponseEntity.ok("Successfully deleted") : ResponseEntity.badRequest().body("Error deleting");
    }
}
