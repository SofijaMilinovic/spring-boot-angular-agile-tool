package com.agileapp.repository;

import com.agileapp.entity.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRoleRepository extends JpaRepository<ProjectRole, Long> {

    Optional<ProjectRole> findByCode(String code);
}
