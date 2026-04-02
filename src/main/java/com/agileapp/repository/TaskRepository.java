package com.agileapp.repository;

import com.agileapp.entity.Project;
import com.agileapp.entity.Task;
import com.agileapp.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
     List<Task> findByProject(Project project);
     List<Task> findByStatus(TaskStatus status);
     List<Task> findByProject_ProjectId(Integer projectId);

}
