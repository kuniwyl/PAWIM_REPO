package com.example.demo.controlers;

import com.example.demo.models.Comment;
import com.example.demo.records.requests.CommentCreateRequest;
import com.example.demo.records.responses.CommentResponse;
import com.example.demo.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/show")
    public List<CommentResponse.CommentResponseRecord> all(){
        return commentService.getAll();
    }

    @GetMapping("/show/{id}")
    public CommentResponse.CommentResponseRecord one(@PathVariable long id){
        return commentService.getOne(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        commentService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public CommentResponse.CommentResponseRecord update(@PathVariable long id, @RequestBody CommentCreateRequest newComment){
        return commentService.edit(id, newComment);
    }
}
