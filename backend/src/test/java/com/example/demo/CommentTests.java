package com.example.demo;

import com.example.demo.models.Comment;
import com.example.demo.records.requests.CommentCreateRequest;
import com.example.demo.records.responses.CommentResponse;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.services.CommentService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CommentTests {
    @Mock
    private CommentRepository repository;

    @InjectMocks
    private CommentService controller;

    private CommentResponse commentResponse = new CommentResponse();

    @Test
    public void get_all_Komentarzes_from_repository_data_in_repo(){
        // arange
        repository = mock(CommentRepository.class);
        controller = new CommentService(repository);
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());
        when(repository.findAll()).thenReturn(comments);
        List<CommentResponse.CommentResponseRecord> commentResponseRecords = comments.stream().map(comment ->
                commentResponse.create(comment)).toList();

        // act
        List<CommentResponse.CommentResponseRecord> result = controller.getAll();

        // assert
        Assert.assertEquals(commentResponseRecords, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void get_all_Komentarzes_from_repository_without_data(){
        // arange
        repository = mock(CommentRepository.class);
        controller = new CommentService(repository);
        List<Comment> comments = new ArrayList<>();
        when(repository.findAll()).thenReturn(comments);
        List<CommentResponse.CommentResponseRecord> commentResponseRecords = comments.stream().map(comment ->
                commentResponse.create(comment)).toList();

        // act
        List<CommentResponse.CommentResponseRecord> result = controller.getAll();

        // assert
        Assert.assertEquals(commentResponseRecords, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void get_one_Komentarze_from_repository(){
        // arange
        repository = mock(CommentRepository.class);
        controller = new CommentService(repository);
        Comment comment = new Comment();
        when(repository.findById(0L)).thenReturn(Optional.of(comment));
        CommentResponse.CommentResponseRecord expected = commentResponse.create(comment);

        // act
        CommentResponse.CommentResponseRecord result = controller.getOne(0L);

        // assert
        Assert.assertEquals(expected, result);
        verify(repository, times(1)).findById(0L);
    }

    @Test
    public void delete_komentarze() {
        // arange
        repository = mock(CommentRepository.class);
        controller = new CommentService(repository);
        Comment comment = new Comment();
        comment.setId(0L);
        when(repository.findById(comment.getId())).thenReturn(Optional.of(comment));

        // act
        controller.delete(comment.getId());

        // assert
        verify(repository, times(1)).deleteById(comment.getId());
    }

    @Test
    public void delete_not_existing_id() {
        // arange
        repository = mock(CommentRepository.class);
        controller = new CommentService(repository);
        when(repository.findById(1L)).thenReturn(null);

        // act
        controller.delete(1L);

        // assert
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void put_komentarze_on_existing_id() {
        // arange
        repository = mock(CommentRepository.class);
        controller = new CommentService(repository);
        Comment comment = new Comment();
        comment.setId(0L);
        when(repository.existsById(comment.getId())).thenReturn(true);
        when(repository.findById(comment.getId())).thenReturn(Optional.of(comment));
        CommentResponse.CommentResponseRecord expected = commentResponse.create(comment);

        // act
        CommentResponse.CommentResponseRecord result = controller.edit(0L, new CommentCreateRequest(
                comment.getText()
        ));

        // assert
        Assert.assertEquals(expected, result);
        verify(repository, times(1)).save(comment);
        verify(repository, times(1)).findById(comment.getId());
    }
}
