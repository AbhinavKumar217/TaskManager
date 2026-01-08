package com.abhinav.taskmanager.dto;

import com.abhinav.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusUpdateRequest {

    @NotNull
    private TaskStatus status;
}
