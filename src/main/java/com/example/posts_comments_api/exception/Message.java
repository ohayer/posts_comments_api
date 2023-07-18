package com.example.posts_comments_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {

    private int status;
    private String message;

}
