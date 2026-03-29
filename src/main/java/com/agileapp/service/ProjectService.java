package com.agileapp.service;

import com.agileapp.dto.*;
import com.agileapp.entity.*;
import com.agileapp.exception.BadRequestException;
import com.agileapp.exception.ResourceNotFoundException;
import com.agileapp.mapper.ProjectMapper;
import com.agileapp.mapper.ProjectMemberMapper;
import com.agileapp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectStatusRepository projectStatusRepository;

    private final AppUserRepository appUserRepository;
    private  final ProjectRoleRepository projectRoleRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectStatusRepository projectStatusRepository,
                          AppUserRepository appUserRepository, ProjectRoleRepository projectRoleRepository, ProjectMemberRepository projectMemberRepository) {
        this.projectRepository = projectRepository;
        this.projectStatusRepository = projectStatusRepository;
        this.appUserRepository = appUserRepository;
        this.projectRoleRepository =projectRoleRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream() //ovde se lista dobijenih Project objkata pretvara u stream, da bi svaki element mogao da se obradi
                .map(ProjectMapper::mapToDto)//za svaki objekat iz liste poziva metodu i pretvara ga u projectResponseDTO
                .toList(); //mapirane DTO objekte skuplja nazad u listu
    }
    public ProjectResponseDTO getProjectById(Integer projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project not found"));

        return ProjectMapper.mapToDto(project);
    }

    public ProjectResponseDTO createProject(CreateProjectRequestDTO request){

        if(request.getStartedAt()!= null && request.getEndAt()!=null && request.getEndAt().isBefore(request.getStartedAt())){
            throw new BadRequestException("Project end date cannot be before start date");
        }

        Project project = new Project();


            ProjectStatus status = projectStatusRepository.findById(request.getStatusId()).orElseThrow(() -> new ResourceNotFoundException("Project status not found"));

            project.setProjectStatus(status);
            project.setDescription(request.getDescription());
            project.setName(request.getName());
            project.setStartedAt(request.getStartedAt());
            project.setEndAt(request.getEndAt());


        Project savedProject = projectRepository.save(project); //repository upisuje novi objekat u bazu

        return ProjectMapper.mapToDto(savedProject);// ovde poyivam metodu koja ce savedProject koji je entity objekat pretvoriti u DTO objekat
    }

    public ProjectResponseDTO updateProject(Integer projectId, UpdateProjectRequestDTO request) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        LocalDateTime newStartedAt = request.getStartedAt()!=null ? request.getStartedAt(): project.getStartedAt();

        LocalDateTime newEndAt = request.getEndAt()!=null ? request.getEndAt(): project.getEndAt();

        if(newStartedAt != null && newStartedAt!=null && newEndAt.isBefore(newStartedAt)){
            throw  new BadRequestException("Project end date cannot be before start date");
        }

        if(request.getStatusId()!=null){
            ProjectStatus status = projectStatusRepository.findById(request.getStatusId()).orElseThrow(() -> new ResourceNotFoundException("Project status not found"));
            project.setProjectStatus(status);

        }
        if(request.getDescription()!=null){
           project.setDescription(request.getDescription());
        }
        if(request.getName()!=null){
            project.setName(request.getName());
        }
        if(request.getStartedAt()!=null){
            project.setStartedAt(request.getStartedAt());
        }
        if(request.getEndAt()!=null){
            project.setEndAt(request.getEndAt());
        }


        Project updatedProject = projectRepository.save((project));

        return ProjectMapper.mapToDto(updatedProject);

    }

    public ProjectResponseDTO cancelProject(Integer projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new ResourceNotFoundException("Project not found"));

        ProjectStatus canceledStatus = projectStatusRepository.findByCode("CANCELLED").orElseThrow(()-> new ResourceNotFoundException("Canceled status not found"));

        project.setProjectStatus(canceledStatus);
        Project canceledProject = projectRepository.save(project);

        return ProjectMapper.mapToDto(canceledProject);

    }

    public ProjectResponseDTO finishProject(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        ProjectStatus finishedStatus = projectStatusRepository.findByCode("COMPLETED").orElseThrow(()-> new ResourceNotFoundException("Comleted status not found"));

        project.setProjectStatus(finishedStatus);
        Project finishedProject = projectRepository.save(project);

        return ProjectMapper.mapToDto(finishedProject);
    }

    public void addMember(Integer projectId, AddProjectMemberRequestDTO request){

        Project project =  projectRepository.findById(projectId).orElseThrow(()-> new ResourceNotFoundException("Project not found"));
        AppUser user = appUserRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        ProjectRole role =projectRoleRepository.findById(request.getProjectRoleId()).orElseThrow(() -> new ResourceNotFoundException("Project role not found"));

        ProjectMemberId id =  new ProjectMemberId(projectId, request.getUserId());

        if(projectMemberRepository.existsById(id)){
            throw new BadRequestException("User is already a member of this project");
        }

        ProjectMember member =  new ProjectMember();
        member.setId(id);
        member.setProject(project);
        member.setUser(user);
        member.setProjectRole(role);

        projectMemberRepository.save(member);


    }

    public void removeMember(Integer projectId, Integer userId){

        ProjectMemberId id =  new ProjectMemberId(projectId, userId);

        if(!projectMemberRepository.existsById(id)){
            throw  new ResourceNotFoundException("Project memeber not found");
        }

        projectMemberRepository.deleteById(id);

    }

    public List<ProjectMemberResponseDTO> getMembersOfProject(Integer projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new ResourceNotFoundException("Project not found"));

        List<ProjectMember> members = projectMemberRepository.findByProject_ProjectId(projectId);

        return members.stream().map(ProjectMemberMapper::mapToDto).toList();

    }



}
