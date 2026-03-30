package com.agileapp.dto;

import jakarta.validation.constraints.Size;

public class UpdateUserRequestDTO {

    @Size(max = 60, message = "Username must not exceed 60 characters")
    private String username;
    @Size(max = 255, message = "Password must not exceed 255 characters")
    private String password;
    @Size(max = 120, message = "Full name must not exceed 120 characters")
    private String fullName;

    public UpdateUserRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
