package com.agileapp.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="task_assignee")
public class TaskAssignee {

    @EmbeddedId
    private TaskAssigneeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("taskId")
    @JoinColumn(name="task_id", nullable = false)
    private Task task;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name="user_id",nullable = false)
    private AppUser user;


    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;


    public TaskAssignee() {
    }

    public TaskAssigneeId getId() {
        return id;
    }

    public void setId(TaskAssigneeId id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }
}
