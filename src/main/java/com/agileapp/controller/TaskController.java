package com.agileapp.controller;

import com.agileapp.dto.*;
import com.agileapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponseDTO createTask(@Valid @RequestBody CreateTaskRequestDTO request){
        return taskService.createTask(request);
    }
    @GetMapping
    public List<TaskResponseDTO> getAllTasks(){
        return taskService.getAllTasks();
    }
     @GetMapping("/project/{projectId}")
    public List<TaskResponseDTO> getTaskByProject(@PathVariable Integer projectId){
        return taskService.getTaskByProject(projectId);
    }
    @GetMapping("/user/{userId}")
    public  List<TaskResponseDTO> getTaskByUser(@PathVariable Integer userId){
        return taskService.getTaskByUser(userId);
    }
    @GetMapping("/{taskId}")
    public TaskResponseDTO getTaskById(@PathVariable Integer taskId){
        return taskService.getTaskById(taskId);
    }
    @PatchMapping("/{taskId}")
    public TaskResponseDTO updateTask(@PathVariable Integer taskId, @RequestBody UpdateTaskRequestDTO request){
        return taskService.updateTask(taskId, request);
    }

    @PatchMapping("/{taskId}/status")
    public TaskResponseDTO updateTaskStatus(@PathVariable Integer taskId, @RequestBody UpdateTaskStatusRequestDTO request){
        return taskService.updateTaskStatus(taskId, request);
    }
    @PatchMapping("/{taskId}/priority")
    public TaskResponseDTO updateTaskPriority(@PathVariable Integer taskId, @RequestBody UpdateTaskPriorityRequestDTO request){
        return taskService.updateTaskPriority(taskId, request);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Integer taskId){
         taskService.deleteTask(taskId);
    }

    @PostMapping("/{taskId}/assignees")
    public void assignTaskToUser(@PathVariable Integer taskId, @Valid @RequestBody AssignTaskToUserRequestDTO request){
        taskService.assignTaskToUser(taskId, request);

    }
    @PostMapping("/{taskId}/assignees/{userId}")
    public void removeUserFromTask(@PathVariable Integer taskId, @PathVariable Integer userId){
        taskService.removeUserFromTask(taskId,userId);
    }

}
