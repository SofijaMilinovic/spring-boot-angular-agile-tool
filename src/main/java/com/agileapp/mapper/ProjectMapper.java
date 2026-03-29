package com.agileapp.mapper;

import com.agileapp.dto.ProjectResponseDTO;
import com.agileapp.entity.Project;

public class ProjectMapper {

    public static ProjectResponseDTO mapToDto(Project project){
        ProjectResponseDTO dto = new ProjectResponseDTO();
        dto.setProjectId(project.getProjectId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setStartedAt(project.getStartedAt());
        dto.setEndAt(project.getEndAt());
        dto.setCreatedAt(project.getCreatedAt());
        dto.setUpdatedAt(project.getUpdatedAt());

        if(project.getProjectStatus()!=null){
            dto.setStatusCode(project.getProjectStatus().getCode());
            dto.setStatusName(project.getProjectStatus().getName());
        }

        return dto;
    }
}
