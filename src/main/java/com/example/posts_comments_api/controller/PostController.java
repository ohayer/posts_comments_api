package com.example.posts_comments_api.controller;

import com.example.posts_comments_api.entity.Post;
import com.example.posts_comments_api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> getPosts() {
        List<Post> posts = postService.getPostRepository().findAllByOrderByDateOfAdditionDesc();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        post.setTitle(postService.changeSecondWordToLarge(post.getTitle()));
        postService.getPostRepository().save(post);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(post + "\n" + "added successfully");
    }


}
