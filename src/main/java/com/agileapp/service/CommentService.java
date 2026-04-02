package com.agileapp.service;

import com.agileapp.dto.CommentResponseDTO;
import com.agileapp.dto.CreateCommentRequestDTO;
import com.agileapp.dto.UpdateCommentRequestDTO;
import com.agileapp.entity.AppUser;
import com.agileapp.entity.Comment;
import com.agileapp.entity.Task;
import com.agileapp.exception.ResourceNotFoundException;
import com.agileapp.mapper.CommentMapper;
import com.agileapp.repository.AppUserRepository;
import com.agileapp.repository.CommentRepository;
import com.agileapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final TaskRepository taskRepository ;
    private final AppUserRepository appUserRepository;
    private final CommentRepository commentRepository;


    public CommentService(TaskRepository taskRepository, AppUserRepository appUserRepository,CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
        this.commentRepository = commentRepository;
    }

    public CommentResponseDTO addComment(Integer taskId, CreateCommentRequestDTO request){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        AppUser user = appUserRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Comment comment =  new Comment();
        comment.setText(request.getText());
        comment.setUser(user);
        comment.setTask(task);
        //comment.setUpdatedAt(null);

        Comment savedComment = commentRepository.save(comment);

        return CommentMapper.mapToDto(savedComment);

    }

    public CommentResponseDTO updateComment(Integer commentId, UpdateCommentRequestDTO request){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        if(request.getText()!=null){
            comment.setText(request.getText());
        }

        Comment updatedComment = commentRepository.save(comment);

        return CommentMapper.mapToDto(updatedComment);
    }

    public void deleteComment(Integer commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        commentRepository.delete(comment);

    }

    public List<CommentResponseDTO> getCommentsByTask(Integer taskId){
        List<Comment> comments = commentRepository.findByTask_TaskId(taskId);

        return comments.stream().map(CommentMapper::mapToDto).toList();

    }
}
