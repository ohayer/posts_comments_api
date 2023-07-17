package com.example.posts_comments_api.controller;

import com.example.posts_comments_api.entity.Comment;
import com.example.posts_comments_api.exception.CommentNotFoundException;
import com.example.posts_comments_api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> getComments() {
        List<Comment> comments = postService.getCommentRepository().findAll();
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        postService.getCommentRepository().save(comment);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(comment + ",\n" + "added successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeComment(@PathVariable long id) {
        postService.getCommentRepository().deleteById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateComment(@PathVariable long id, @RequestBody Comment updatedComment) {
        try {
            postService.updateComment(id, updatedComment);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Comment updated successfully");
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
