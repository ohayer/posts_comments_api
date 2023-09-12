package com.example.posts_comments_api.repository;

import com.example.posts_comments_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
