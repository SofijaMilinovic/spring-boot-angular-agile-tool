package com.agileapp.dto;

public class AddProjectMemberRequestDTO {

    private Integer userId;
    private Long projectRoleId;

    public AddProjectMemberRequestDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getProjectRoleId() {
        return projectRoleId;
    }

    public void setProjectRoleId(Long projectRoleId) {
        this.projectRoleId = projectRoleId;
    }
}
