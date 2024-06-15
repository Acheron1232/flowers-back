package com.acheron.flowers.store.service;

import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.exception.custom.UserNotFoundException;
import com.acheron.flowers.security.service.UserService;
import com.acheron.flowers.store.dto.CommentSaveDto;
import com.acheron.flowers.store.entity.ShopComment;
import com.acheron.flowers.store.repository.ShopCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShopCommentService {
    private final ShopCommentRepository shopCommentRepository;
    private final UserService userService;

    public ResponseEntity<?> save(CommentSaveDto commentSaveDto, String username) {
        try {
            User user = userService.findByEmail(username).orElseThrow(UserNotFoundException::new);
            return ResponseEntity.ok(shopCommentRepository.save(new ShopComment(
                    null,
                    user,
                    LocalDateTime.now(),
                    commentSaveDto.getText()
            )));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> delete(Long id, Principal principal) {

        if (id != null && shopCommentRepository.existsById(id)) {
            User user = userService.findByEmail(principal.getName()).orElseThrow(UserNotFoundException::new);
            ShopComment shopComment = shopCommentRepository.findById(id).orElseThrow();
            if(!shopComment.getAuthor().equals(user)){
                return ResponseEntity.status(403).body("Forbidden");
            }
            try {
                shopCommentRepository.deleteById(id);
                return ResponseEntity.ok("Success");
            } catch (Exception e) {
                return ResponseEntity.status(403).body(e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("Comment with id: " + id + " does not exists");
    }
}
