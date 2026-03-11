package com.agileapp.controller;

import com.agileapp.dto.CreateProjectRequestDTO;
import com.agileapp.dto.ProjectResponseDTO;
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
}
