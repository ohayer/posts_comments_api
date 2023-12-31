package com.example.posts_comments_api.repository;

import com.example.posts_comments_api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post ORDER BY date_of_addition DESC", nativeQuery = true)
    List<Post> findAllByOrderByDateOfAdditionDesc();
    @Query(value = ("SELECT * FROM post p INNER JOIN post_comments c ON p.id = c.post_id WHERE c.comments_id = ?1"), nativeQuery = true)
    Post findPostByCommentId(Long id);
}
