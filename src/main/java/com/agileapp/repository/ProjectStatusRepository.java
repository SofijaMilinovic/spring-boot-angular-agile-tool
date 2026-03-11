package com.agileapp.repository;

import com.agileapp.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {

    Optional<ProjectStatus> findByCode(String code);
}
