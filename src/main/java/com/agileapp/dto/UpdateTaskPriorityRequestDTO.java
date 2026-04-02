package com.agileapp.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateTaskPriorityRequestDTO {

    @NotNull(message = "Priority id is required")
    private Integer priorityId;

    public UpdateTaskPriorityRequestDTO() {
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }
}
