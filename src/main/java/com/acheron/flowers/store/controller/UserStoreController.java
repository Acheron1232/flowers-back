package com.acheron.flowers.store.controller;

import com.acheron.flowers.store.dto.CommentSaveDto;
import com.acheron.flowers.store.service.CommentService;
import com.acheron.flowers.store.service.ShopCommentService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class UserStoreController {
    private final ShopCommentService shopCommentService;
    private final CommentService commentService;
    @PostMapping("/shopComment")
    public ResponseEntity<?> saveShopComment(@RequestBody CommentSaveDto commentSaveDto, Principal principal){
        return shopCommentService.save(commentSaveDto, principal.getName());
    }
    @PostMapping("/comment/{id}")
    public ResponseEntity<?> saveComment(@RequestBody CommentSaveDto commentSaveDto, Principal principal, @PathVariable Long id){
        return commentService.save(commentSaveDto, principal.getName(),id);
    }

    @DeleteMapping("/shopComment")
    public ResponseEntity<?> deleteShopComment(@RequestBody ObjectNode objectNode,Principal principal) {
        try {
            return shopCommentService.delete(objectNode.get("id").asLong(),principal);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Id is not correct");
        }
    }
    @DeleteMapping("/comment")
    public ResponseEntity<?> deleteComment(@RequestBody ObjectNode objectNode,Principal principal) {
        try {
            return commentService.delete(objectNode.get("id").asLong(),principal);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Id is not correct");
        }
    }
}
