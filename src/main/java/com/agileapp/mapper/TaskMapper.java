package com.agileapp.mapper;

import com.agileapp.dto.TaskResponseDTO;
import com.agileapp.entity.Task;

public class TaskMapper {

    public static TaskResponseDTO mapToDto(Task task){

        TaskResponseDTO dto =  new TaskResponseDTO();
        dto.setTaskId(task.getTaskId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());

        if(task.getProject()!=null){
            dto.setProjectId(task.getProject().getProjectId());
        }

        if(task.getStatus()!=null){
            dto.setStatusId(task.getStatus().getId());
            dto.setStatusName(task.getStatus().getName());
            dto.setStatusCode(task.getStatus().getCode());
        }

        if(task.getPriority()!=null){
            dto.setPriorityId(task.getPriority().getId());
            dto.setPriorityName(task.getPriority().getName());
            dto.setPriorityCode(task.getPriority().getCode());
        }
        dto.setCreatedAt(task.getCreated_at());
        dto.setFinishedA(task.getFinished_at());

        return dto;
    }


}
