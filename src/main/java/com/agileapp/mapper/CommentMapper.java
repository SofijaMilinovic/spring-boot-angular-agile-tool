package com.agileapp.mapper;

import com.agileapp.dto.CommentResponseDTO;
import com.agileapp.entity.Comment;

public class CommentMapper {

    public static CommentResponseDTO mapToDto(Comment comment){
        CommentResponseDTO dto = new CommentResponseDTO();

        dto.setCommentId(comment.getCommentId());
        dto.setText(comment.getText());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUpdatedAt(comment.getUpdatedAt());

        if(comment.getUser()!=null){
            dto.setUserId(comment.getUser().getUserId());
            dto.setUsername(comment.getUser().getUsername());
            dto.setFullName(comment.getUser().getFullName());
        }
        if(comment.getTask()!=null){
            dto.setTaskId(comment.getTask().getTaskId());
        }



        return dto;

    }
}
