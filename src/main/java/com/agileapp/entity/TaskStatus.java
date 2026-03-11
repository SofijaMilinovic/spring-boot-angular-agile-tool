package com.agileapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="task_status")
public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable=false, length = 30, unique = true)
    private String code;

    @Column(nullable = false,length = 50)
    private String name;

    public TaskStatus() {
    }

    public Integer getId() {
        return Id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
