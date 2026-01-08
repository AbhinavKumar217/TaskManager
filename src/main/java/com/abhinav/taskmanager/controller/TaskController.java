package com.abhinav.taskmanager.controller;

import com.abhinav.taskmanager.dto.CreateTaskRequest;
import com.abhinav.taskmanager.dto.TaskResponse;
import com.abhinav.taskmanager.entity.Task;
import com.abhinav.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody CreateTaskRequest request) {

        Task task = taskService.createTask(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TaskResponse.from(task));
    }
}
