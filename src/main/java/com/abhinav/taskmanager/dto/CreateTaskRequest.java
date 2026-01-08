package com.abhinav.taskmanager.dto;

import com.abhinav.taskmanager.entity.TaskPriority;
import com.abhinav.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateTaskRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    private TaskStatus status;
    private TaskPriority priority;

    private LocalDate dueDate;

    private Long assignedToUserId;
}
