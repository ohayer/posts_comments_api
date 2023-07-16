package com.example.posts_comments_api.repository;

import com.example.posts_comments_api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM Post ORDER BY date_of_addition DESC", nativeQuery = true)
    List<Post> findAllByOrderByDateOfAdditionDesc();
}
