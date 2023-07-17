package com.example.posts_comments_api.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CommentNotFoundAdvice {

    public Message commentNotFoundHandler(CommentNotFoundException ex){
        return new Message(404, ex.getMessage());
    }
}
