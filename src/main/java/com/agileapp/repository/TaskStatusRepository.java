package com.agileapp.repository;

import com.agileapp.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer> {

    Optional<TaskStatus> findByCode(String code);
}
