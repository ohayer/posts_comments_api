package com.example.posts_comments_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Getter
@AllArgsConstructor
public class Message {

    private int status;
    private String message;

}
