package com.example.posts_comments_api.service;

import com.example.posts_comments_api.entity.Comment;
import com.example.posts_comments_api.entity.Post;
import com.example.posts_comments_api.exception.CommentNotFoundException;
import com.example.posts_comments_api.repository.CommentRepository;
import com.example.posts_comments_api.repository.PostRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PostService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public String changeSecondWordToLarge(String title) {
        String[] words = title.split(" ");
        if (words.length >= 2) {
            words[1] = words[1].substring(0, 1).toUpperCase() + words[1].substring(1);
        }
        return String.join(" ", words);
    }

    public void updateComment(long id, Comment updatedComment) {
        Optional<Comment> commentOptional = commentRepository.findById(id);

        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setDescription(updatedComment.getDescription());
            comment.setDate_of_addition(updatedComment.getDate_of_addition());

            commentRepository.save(comment);
        } else {
            throw new CommentNotFoundException("Comment not found");
        }
    }

    public ResponseEntity<?> addCommentToPost(Comment comment){

        Optional<Post> foundPost = postRepository.findById(comment.getPost_id());
        if (foundPost.isPresent()) {
        comment = commentRepository.save(comment);
            Post post = foundPost.get();

            List<Comment> comments = post.getComments();
            comments.add(comment);
            post.setComments(comments);

            postRepository.save(post);

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(comment + ",\n" + "added successfully");
        } else {
            throw new CommentNotFoundException("Comment cannot be added");
        }
    }
}
