package com.agileapp.controller;

import com.agileapp.dto.*;
import com.agileapp.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponseDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public ProjectResponseDTO createProject(@Valid @RequestBody CreateProjectRequestDTO request){
        return projectService.createProject(request);
    }

    @GetMapping("/{projectId}")
    public ProjectResponseDTO getProjectById(@PathVariable Integer projectId){
        return projectService.getProjectById(projectId);
    }

    @PatchMapping("/{projectId}")
    public ProjectResponseDTO updateProject(@PathVariable Integer projectId, @RequestBody UpdateProjectRequestDTO request){
        return projectService.updateProject(projectId, request);
    }

    @PatchMapping("/{projectId}/cancel")
    public ProjectResponseDTO cancelProject(@PathVariable Integer projectId){
        return projectService.cancelProject(projectId);
    }

    @PatchMapping("/{projectId}/finish")
    public ProjectResponseDTO finishProject(@PathVariable Integer projectId){
        return projectService.cancelProject(projectId);
    }

    @PostMapping("/{projectId}/members")
    public void addMember(@PathVariable Integer projectId, @RequestBody AddProjectMemberRequestDTO request){
        projectService.addMember(projectId, request);
    }

    @DeleteMapping("/{projectId}/members/{userId}")
    public void removeMember(@PathVariable Integer projectId, @PathVariable Integer userId){
        projectService.removeMember(projectId, userId);
    }

    @GetMapping("/{projectId}/members")
    public List<ProjectMemberResponseDTO> getMembersOfProject(@PathVariable Integer projectId){
        return projectService.getMembersOfProject(projectId);
    }


}
