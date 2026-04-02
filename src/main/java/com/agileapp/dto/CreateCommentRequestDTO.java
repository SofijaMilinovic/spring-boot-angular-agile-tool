package com.agileapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateCommentRequestDTO {

    @NotBlank(message = "Comment is required")
    @Size(max = 2000, message = "Comment can not exceed 2000 characters")
    private String text;

    @NotNull(message = "User id is required")
    private Integer userId;

    public CreateCommentRequestDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
