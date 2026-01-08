package com.abhinav.taskmanager.controller;

import com.abhinav.taskmanager.dto.CreateTaskRequest;
import com.abhinav.taskmanager.dto.TaskResponse;
import com.abhinav.taskmanager.entity.Task;
import com.abhinav.taskmanager.entity.TaskPriority;
import com.abhinav.taskmanager.entity.TaskStatus;
import com.abhinav.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public Page<TaskResponse> getTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(required = false) Long assignedToUserId,
            Pageable pageable
    ) {
        return taskService
                .getTasks(status, priority, assignedToUserId, pageable)
                .map(TaskResponse::from);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return TaskResponse.from(taskService.getTaskById(id));
    }

}
