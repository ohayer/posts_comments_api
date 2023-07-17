package com.example.posts_comments_api.service;

import com.example.posts_comments_api.entity.Comment;
import com.example.posts_comments_api.exception.CommentNotFoundException;
import com.example.posts_comments_api.repository.CommentRepository;
import com.example.posts_comments_api.repository.PostRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@Data
public class PostService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public String changeSecondWordToLarge(String title){
        String[] words = title.split(" ");
        if (words.length >=2){
            words[1] = words[1].substring(0,1).toUpperCase() + words[1].substring(1);
        }
        return String.join(" ",words);
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
}
