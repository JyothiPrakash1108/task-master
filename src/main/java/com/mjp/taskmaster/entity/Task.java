package com.mjp.taskmaster.entity;

import java.time.LocalDateTime;

import com.mjp.taskmaster.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    private String taskName;
    private String taskDescription;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    private LocalDateTime taskCreatedTime;
    private LocalDateTime taskDueDate;
    // task -> project
    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    private Project project;
    @ManyToOne
    @JoinColumn(name = "created_by",nullable = false)
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;
    
    public Task() {
    }
    public Task(Long taskId, String taskName, String taskDescription, TaskStatus taskStatus,
            LocalDateTime taskCreatedTime, LocalDateTime taskDueDate, Project project, User createdBy,
            User assignedTo) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskCreatedTime = taskCreatedTime;
        this.taskDueDate = taskDueDate;
        this.project = project;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
    }
    public Long getTaskId() {
        return taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
    public LocalDateTime getTaskCreatedTime() {
        return taskCreatedTime;
    }
    public void setTaskCreatedTime(LocalDateTime taskCreatedTime) {
        this.taskCreatedTime = taskCreatedTime;
    }
    public LocalDateTime getTaskDueDate() {
        return taskDueDate;
    }
    public void setTaskDueDate(LocalDateTime taskDueDate) {
        this.taskDueDate = taskDueDate;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public User getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    public User getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }
    
}
