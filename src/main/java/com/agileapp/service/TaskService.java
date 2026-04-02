package com.agileapp.service;

import com.agileapp.dto.*;
import com.agileapp.entity.*;
import com.agileapp.exception.BadRequestException;
import com.agileapp.exception.ResourceNotFoundException;
import com.agileapp.mapper.TaskMapper;
import com.agileapp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final TaskPriorityRepository taskPriorityRepository;
    private final TaskAssigneeRepository taskAssigneeRepository;
    private final AppUserRepository appUserRepository;


    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, TaskStatusRepository taskStatusRepository,
                       TaskPriorityRepository taskPriorityRepository,TaskAssigneeRepository taskAssigneeRepository,AppUserRepository appUserRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.taskPriorityRepository = taskPriorityRepository;
        this.taskAssigneeRepository = taskAssigneeRepository;
        this.appUserRepository = appUserRepository;
    }

    public TaskResponseDTO createTask(CreateTaskRequestDTO request){
        Task task = new Task();

        Project project = projectRepository.findById(request.getProjectId()).orElseThrow(()-> new ResourceNotFoundException("Project not found"));
        TaskStatus status = taskStatusRepository.findById(request.getStatusId()).orElseThrow(()-> new ResourceNotFoundException("Task status not found"));
        TaskPriority priority = taskPriorityRepository.findById(request.getPriorityId()).orElseThrow(()-> new ResourceNotFoundException("Task priority not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setProject(project);
        task.setStatus(status);
        task.setPriority(priority);

        Task savedTask = taskRepository.save(task);

        return TaskMapper.mapToDto(savedTask);
    }

    public List<TaskResponseDTO> getTaskByProject(Integer projectId){
        List<Task> tasks = taskRepository.findByProject_ProjectId(projectId);

        return tasks.stream().map(TaskMapper::mapToDto).toList();
    }

    public List<TaskResponseDTO> getTaskByUser(Integer userId){
        List<TaskAssignee> taskAssignees = taskAssigneeRepository.findByUser_UserId(userId);

        return taskAssignees.stream().map(taskAssignee -> TaskMapper.mapToDto(taskAssignee.getTask())).toList();
    }
    public TaskResponseDTO getTaskById(Integer taskId){
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task not found"));

        return TaskMapper.mapToDto(task);
    }

    public List<TaskResponseDTO> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream().map(TaskMapper::mapToDto).toList();
    }

    public void deleteTask(Integer taskId){
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
    public TaskResponseDTO updateTask(Integer taskId, UpdateTaskRequestDTO request){

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        LocalDateTime newFinishedAt = request.getFinishedAt()!=null ?request.getFinishedAt(): task.getFinished_at();

        if(task.getCreated_at()!= null && newFinishedAt!=null && newFinishedAt.isBefore(task.getCreated_at())){
            throw new BadRequestException("Task finish date cannot be before created date");
        }
        task.setFinished_at(newFinishedAt);

        if(request.getTitle()!=null){
            task.setTitle(request.getTitle());
        }
        if(request.getDescription()!=null){
            task.setDescription((request.getDescription()));
        }
        if(request.getStatusId()!=null){
            TaskStatus status = taskStatusRepository.findById(request.getStatusId()).orElseThrow(()-> new ResourceNotFoundException("Status not found"));
            task.setStatus(status);
        }
        if(request.getPriorityId()!=null){
            TaskPriority priority = taskPriorityRepository.findById(request.getPriorityId()).orElseThrow(()-> new ResourceNotFoundException("Priority not found"));
            task.setPriority(priority);
        }
        Task updatedTask = taskRepository.save(task);

        return TaskMapper.mapToDto(updatedTask);
    }

    public TaskResponseDTO updateTaskStatus(Integer taskId, UpdateTaskStatusRequestDTO request){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        TaskStatus status = taskStatusRepository.findById(request.getStatusId()).orElseThrow(() -> new ResourceNotFoundException("Task status not found"));

        task.setStatus(status);

        Task updatedTask = taskRepository.save(task);

        return TaskMapper. mapToDto(updatedTask);
    }

    public TaskResponseDTO updateTaskPriority(Integer taskId, UpdateTaskPriorityRequestDTO request){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        TaskPriority priority = taskPriorityRepository.findById(request.getPriorityId()).orElseThrow(() -> new ResourceNotFoundException("Task priority not found"));

        task.setPriority(priority);

        Task updatedTask = taskRepository.save(task);

        return TaskMapper.mapToDto(updatedTask);
    }

    public void assignTaskToUser(Integer taskId, AssignTaskToUserRequestDTO request){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        AppUser user = appUserRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        TaskAssigneeId id = new TaskAssigneeId(taskId,request.getUserId());

        if(taskAssigneeRepository.existsById(id)){
            throw new BadRequestException("User is already assigned to this task");
        }

        TaskAssignee taskAssignee =  new TaskAssignee();
        taskAssignee.setId(id);
        taskAssignee.setUser(user);
        taskAssignee.setTask(task);
        taskAssignee.setAssignedAt(LocalDateTime.now());

        taskAssigneeRepository.save(taskAssignee);
    }

    public void removeUserFromTask(Integer taskId, Integer userId){

        TaskAssigneeId id = new TaskAssigneeId(taskId,userId);

        if(!taskAssigneeRepository.existsById(id)){
            throw new ResourceNotFoundException("Task assignee not found");
        }

        taskAssigneeRepository.deleteById(id);
    }


}