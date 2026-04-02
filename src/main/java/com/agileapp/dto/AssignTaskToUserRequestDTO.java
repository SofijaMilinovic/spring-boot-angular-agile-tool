package com.agileapp.dto;

import jakarta.validation.constraints.NotNull;

public class AssignTaskToUserRequestDTO {

    @NotNull(message = "User not found")
    private Integer userId;

    public AssignTaskToUserRequestDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
