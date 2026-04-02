package com.agileapp.repository;

import com.agileapp.entity.Task;
import com.agileapp.entity.TaskAssignee;
import com.agileapp.entity.TaskAssigneeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAssigneeRepository extends JpaRepository<TaskAssignee, TaskAssigneeId> {

    List<TaskAssignee> findByTask_TaskId(Integer taskId);
    List<TaskAssignee> findByUser_UserId(Integer userId);
}
