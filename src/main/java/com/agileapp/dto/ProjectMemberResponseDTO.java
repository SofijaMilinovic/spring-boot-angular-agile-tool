package com.agileapp.dto;

public class ProjectMemberResponseDTO {

    private Integer userId;
    private String username;
    private String fullName;
    private Long projectRoleId;
    private String projectRoleCode;
    private String projectRoleName;

    public ProjectMemberResponseDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getProjectRoleId() {
        return projectRoleId;
    }

    public void setProjectRoleId(Long projectRoleId) {
        this.projectRoleId = projectRoleId;
    }

    public String getProjectRoleCode() {
        return projectRoleCode;
    }

    public void setProjectRoleCode(String projectRoleCode) {
        this.projectRoleCode = projectRoleCode;
    }

    public String getProjectRoleName() {
        return projectRoleName;
    }

    public void setProjectRoleName(String projectRoleName) {
        this.projectRoleName = projectRoleName;
    }
}
