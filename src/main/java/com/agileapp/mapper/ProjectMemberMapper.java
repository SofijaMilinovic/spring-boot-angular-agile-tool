package com.agileapp.mapper;

import com.agileapp.dto.ProjectMemberResponseDTO;
import com.agileapp.entity.ProjectMember;

public class ProjectMemberMapper {

    public static ProjectMemberResponseDTO mapToDto(ProjectMember projectMember){
        ProjectMemberResponseDTO dto = new ProjectMemberResponseDTO();

        dto.setUserId(projectMember.getUser().getUserId());
        dto.setUsername(projectMember.getUser().getUsername());
        dto.setFullName(projectMember.getUser().getFullName());

        dto.setProjectRoleId(projectMember.getProjectRole().getId());
        dto.setProjectRoleCode(projectMember.getProjectRole().getCode());
        dto.setProjectRoleName(projectMember.getProjectRole().getName());

        return dto;
    }
}
