package com.abhinav.taskmanager.service;

import com.abhinav.taskmanager.dto.CreateTaskRequest;
import com.abhinav.taskmanager.entity.Task;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    Task createTask(CreateTaskRequest request);
}
