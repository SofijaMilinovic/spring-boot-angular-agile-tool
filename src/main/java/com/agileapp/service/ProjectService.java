package com.agileapp.service;

import com.agileapp.dto.CreateProjectRequestDTO;
import com.agileapp.dto.ProjectResponseDTO;
import com.agileapp.entity.Project;
import com.agileapp.entity.ProjectStatus;
import com.agileapp.mapper.ProjectMapper;
import com.agileapp.repository.ProjectRepository;
import com.agileapp.repository.ProjectStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectStatusRepository projectStatusRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectStatusRepository projectStatusRepository) {
        this.projectRepository = projectRepository;
        this.projectStatusRepository = projectStatusRepository;
    }

    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream() //ovde se lista dobijenih Project objkata pretvara u stream, da bi svaki element mogao da se obradi
                .map(ProjectMapper::mapToDto)//za svaki objekat iz liste poziva metodu i pretvara ga u projectResponseDTO
                .toList(); //mapirane DTO objekte skuplja nazad u listu
    }

    public ProjectResponseDTO createProject(CreateProjectRequestDTO request){
        ProjectStatus status = projectStatusRepository.findById((request.getStatusId())).orElseThrow(()-> new RuntimeException("Project status not found"));

        Project project = new Project(); // pravi novi Entity objekat za kreiranje na osnovu requesta
        project.setName(request.getName());
        project.setStartedAt(request.getStartedAt());
        project.setEndAt(request.getEndAt());
        project.setProjectStatus((status));

        Project savedProject = projectRepository.save(project); //repository upisuje novi objekat u bazu

        return ProjectMapper.mapToDto(savedProject);// ovde poyivam metodu koja ce savedProject koji je entity objekat pretvoriti u DTO objekat
    }



}
