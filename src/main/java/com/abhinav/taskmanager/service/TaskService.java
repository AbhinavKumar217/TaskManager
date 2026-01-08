package com.abhinav.taskmanager.service;

import com.abhinav.taskmanager.dto.CreateTaskRequest;
import com.abhinav.taskmanager.entity.Task;
import com.abhinav.taskmanager.entity.TaskPriority;
import com.abhinav.taskmanager.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    Task createTask(CreateTaskRequest request);

    Page<Task> getTasks(
            TaskStatus status,
            TaskPriority priority,
            Long assignedToUserId,
            Pageable pageable
    );

    Task getTaskById(Long id);
}
