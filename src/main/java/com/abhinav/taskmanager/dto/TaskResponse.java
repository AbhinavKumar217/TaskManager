package com.abhinav.taskmanager.dto;

import com.abhinav.taskmanager.entity.Task;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private Instant createdAt;
    private Instant updatedAt;
    private Long assignedToUserId;

    public static TaskResponse from(Task task) {
        TaskResponse response = new TaskResponse();
        response.id = task.getId();
        response.title = task.getTitle();
        response.description = task.getDescription();
        response.status = task.getStatus().name();
        response.priority = task.getPriority().name();
        response.dueDate = task.getDueDate();
        response.createdAt = task.getCreatedAt();
        response.updatedAt = task.getUpdatedAt();

        if (task.getAssignedTo() != null) {
            response.assignedToUserId = task.getAssignedTo().getId();
        }

        return response;
    }
}
