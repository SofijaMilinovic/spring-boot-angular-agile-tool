package com.agileapp.dto;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class UpdateProjectRequestDTO {

    @Size(max=120, message = "Project name must not exceed 120 characters")
    private String name;
    private Integer statusId;
    private LocalDateTime startedAt;
    private LocalDateTime endAt;

    @Size(max=120, message = "Description name must not exceed 120 characters")
    private String description;

    public UpdateProjectRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
