package com.example.posts_comments_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate date_of_addition = LocalDate.now();
    @OneToMany(fetch = FetchType.EAGER)
    private List<Comment> comments;

}
