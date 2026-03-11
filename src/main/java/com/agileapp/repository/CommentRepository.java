package com.agileapp.repository;

import com.agileapp.entity.Comment;
import com.agileapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByTask_TaskId(Integer taskId);
}
