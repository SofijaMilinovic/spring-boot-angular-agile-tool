package com.agileapp.controller;

import com.agileapp.dto.CommentResponseDTO;
import com.agileapp.dto.CreateCommentRequestDTO;
import com.agileapp.dto.UpdateCommentRequestDTO;
import com.agileapp.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/tasks/{taskId}/comments")
    public CommentResponseDTO addComment(@PathVariable Integer taskId, @Valid @RequestBody CreateCommentRequestDTO request){
        return commentService.addComment(taskId, request);
    }
    @PatchMapping("/comments/{commentId}")
    public CommentResponseDTO updateComment(@PathVariable Integer commentId, @RequestBody UpdateCommentRequestDTO request){
        return commentService.updateComment(commentId,request);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Integer commentId){
         commentService.deleteComment(commentId);
    }

    @GetMapping("/tasks/{taskId}/comments")
    public List<CommentResponseDTO> getCommentsByTask(@PathVariable Integer taskId){
        return commentService.getCommentsByTask(taskId);
    }
}
