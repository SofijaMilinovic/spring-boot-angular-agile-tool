package com.agileapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateTaskRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(max=200, message = "Title must not exceed 200 characters")
    private String title;

    @Size(max=1000, message = "Description must not exceed 1000 characters")
    private String description;
    @NotNull(message = "Project is required")
    private Integer projectId;

    @NotNull(message = "StatusId is required")
    private Integer statusId;
    @NotNull(message = "PriorityId is required")
    private Integer priorityId;

    public CreateTaskRequestDTO() {
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
}
