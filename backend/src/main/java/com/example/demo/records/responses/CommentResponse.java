package com.example.demo.records.responses;

import com.example.demo.models.Comment;

public class CommentResponse {

    public record CommentResponseRecord (
            long id,
            String text,
            String date
    ){};

    public CommentResponse(){}

    public CommentResponseRecord create(Comment comment){
        return new CommentResponseRecord(
                comment.getId(),
                comment.getText(),
                comment.getDate()
        );
    }


}
