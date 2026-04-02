package com.agileapp.dto;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class UpdateTaskRequestDTO {

    @Size(max=200, message = "Title must not exceed 200 characters")
    private String title;
    @Size(max=1000, message = "Description must not exceed 1000 characters")
    private String description;
    private Integer statusId;
    private Integer priorityId;
    private LocalDateTime finishedAt;

    public UpdateTaskRequestDTO() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }
}
