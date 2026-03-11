package com.agileapp.repository;

import com.agileapp.entity.ProjectMember;
import com.agileapp.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId>{


    List<ProjectMember> findByProject_ProjectId(Integer projectId);

    List<ProjectMember> findByUser_UserId(Integer userId);
}
