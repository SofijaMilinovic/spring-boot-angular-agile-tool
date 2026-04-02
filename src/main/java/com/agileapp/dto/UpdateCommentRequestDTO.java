package com.agileapp.dto;

import jakarta.validation.constraints.Size;

public class UpdateCommentRequestDTO {

    @Size(max = 2000,message = "Comment text must not exceed 2000 characters")
    private String text;

    public UpdateCommentRequestDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
