package com.agileapp.repository;

import com.agileapp.entity.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskPriorityRepository  extends JpaRepository<TaskPriority, Integer> {

    Optional<TaskPriority> findByCode(String code);
}
