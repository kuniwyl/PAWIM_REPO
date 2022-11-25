package com.example.demo.services;

import com.example.demo.models.Basket;
import com.example.demo.models.Comment;
import com.example.demo.records.requests.CommentCreateRequest;
import com.example.demo.records.responses.CommentResponse;
import com.example.demo.repositories.BasketRepository;
import com.example.demo.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository repository;

    private CommentResponse commentResponse = new CommentResponse();

    public CommentService(CommentRepository commentRepository){
        this.repository = commentRepository;
    }

    public List<CommentResponse.CommentResponseRecord> getAll(){
        List<Comment> comments = repository.findAll();
        return comments.stream().map(comment -> commentResponse.create(comment)).toList();
    }

    public CommentResponse.CommentResponseRecord getOne(long id){
        return commentResponse.create(repository.findById(id).get());
    }

    public CommentResponse.CommentResponseRecord edit(long id, CommentCreateRequest value){
        Comment comment = null;
        if(repository.existsById(id)) {
            comment = repository.findById(id).get();
            comment.setText(value.content());
            repository.save(comment);
        }
        return commentResponse.create(comment);
    }

    public void delete(long id){
        repository.deleteById(id);
    }

}
