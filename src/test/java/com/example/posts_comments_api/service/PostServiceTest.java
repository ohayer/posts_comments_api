package com.example.posts_comments_api.service;

import com.example.posts_comments_api.entity.Comment;
import com.example.posts_comments_api.exception.CommentNotFoundException;
import com.example.posts_comments_api.repository.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void should_changeSecondWordToLarge_works() {
        //given
        String word_1 = "Hello world";
        String word_2 = "Hello World";
        String word_3 = "Hello";
        //when
        String result_1 = postService.changeSecondWordToLarge(word_1);
        String result_2 = postService.changeSecondWordToLarge(word_2);
        String result_3 = postService.changeSecondWordToLarge(word_3);
        //then
        //true
        Assertions.assertEquals(result_1, word_2);
        Assertions.assertEquals(result_2, word_2);
        Assertions.assertEquals(result_3, word_3);

        //not true
        Assertions.assertNotEquals(result_1, word_1);
    }

    @Test
    void should_updateComment_work() {
        //given
        long commentId = 1L;
        String updatedDescription = "Updated comment description";
        LocalDate updatedDate = LocalDate.now();

        Comment existingComment = new Comment();

        existingComment.setId(commentId);
        existingComment.setDescription("Original comment description");
        existingComment.setDate_of_addition(LocalDate.of(2021, 1, 1));

        Comment updatedComment = new Comment();
        updatedComment.setId(commentId);
        updatedComment.setDescription(updatedDescription);
        updatedComment.setDate_of_addition(updatedDate);

        //when
        Mockito.when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        Mockito.when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(updatedComment);

        // Wywołanie metody testowanej
        postService.updateComment(commentId, updatedComment);

        //then
        Assertions.assertEquals(updatedDescription, existingComment.getDescription());
        Assertions.assertEquals(updatedDate, existingComment.getDate_of_addition());


        Mockito.verify(commentRepository, Mockito.times(1)).save(existingComment);
    }
    @Test
    public void testUpdateComment_NonExistingComment_ThrowsException() {
        //given
        long commentId = 1L;
        Comment updatedComment = new Comment();
        updatedComment.setId(commentId);
        updatedComment.setDescription("Updated comment description");
        updatedComment.setDate_of_addition(LocalDate.now());

        //when
        Mockito.when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        //then
        Assertions.assertThrows(CommentNotFoundException.class, () -> postService.updateComment(commentId, updatedComment));


        Mockito.verify(commentRepository, Mockito.never()).save(Mockito.any(Comment.class));
    }
}