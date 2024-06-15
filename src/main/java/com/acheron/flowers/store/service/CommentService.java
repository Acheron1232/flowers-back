package com.acheron.flowers.store.service;

import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.exception.custom.UserNotFoundException;
import com.acheron.flowers.security.service.UserService;
import com.acheron.flowers.store.dto.CommentSaveDto;
import com.acheron.flowers.store.entity.Comment;
import com.acheron.flowers.store.entity.Product;
import com.acheron.flowers.store.exception.ProductNotFoundException;
import com.acheron.flowers.store.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ProductService productService;

    public ResponseEntity<?> save(CommentSaveDto commentSaveDto, String username, Long id) {
        try {
            User user = userService.findByEmail(username).orElseThrow(UserNotFoundException::new);
            if (productService.existsById(id)) {
                Product product = productService.findById(id).orElseThrow();
                return ResponseEntity.ok(commentRepository.save(new Comment(
                        null,
                        user,
                        LocalDateTime.now(),
                        commentSaveDto.getText(),
                        product
                )));
            } else throw new ProductNotFoundException(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> delete(Long id, Principal principal) {
        if (id != null && commentRepository.existsById(id)) {
            User user = userService.findByEmail(principal.getName()).orElseThrow(UserNotFoundException::new);
            Comment comment = commentRepository.findById(id).orElseThrow();
            if(!comment.getAuthor().equals(user)){
                return ResponseEntity.status(403).body("Forbidden");
            }
            try {
                commentRepository.deleteById(id);
                return ResponseEntity.ok("Success");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("Comment with id: " + id + " does not exists");
    }
}
