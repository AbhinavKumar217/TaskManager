package com.abhinav.taskmanager.service.impl;

import com.abhinav.taskmanager.dto.CreateTaskRequest;
import com.abhinav.taskmanager.dto.TaskRequest;
import com.abhinav.taskmanager.entity.Task;
import com.abhinav.taskmanager.entity.TaskPriority;
import com.abhinav.taskmanager.entity.TaskStatus;
import com.abhinav.taskmanager.entity.User;
import com.abhinav.taskmanager.exception.ResourceNotFoundException;
import com.abhinav.taskmanager.repository.TaskRepository;
import com.abhinav.taskmanager.repository.TaskSpecifications;
import com.abhinav.taskmanager.repository.UserRepository;
import com.abhinav.taskmanager.service.TaskService;
import com.abhinav.taskmanager.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public Task createTask(CreateTaskRequest request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        task.setStatus(
                request.getStatus() != null ? request.getStatus() : TaskStatus.TODO
        );
        task.setPriority(
                request.getPriority() != null ? request.getPriority() : TaskPriority.MEDIUM
        );

        task.setDueDate(request.getDueDate());

        if (request.getAssignedToUserId() != null) {
            User user = userRepository.findById(request.getAssignedToUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Assigned user not found")
                    );
            task.setAssignedTo(user);
        }

        return taskRepository.save(task);
    }

    @Override
    public Page<Task> getTasks(
            TaskStatus status,
            TaskPriority priority,
            Long assignedToUserId,
            Pageable pageable
    ) {
        Specification<Task> spec = Specification
                .where(TaskSpecifications.hasStatus(status))
                .and(TaskSpecifications.hasPriority(priority))
                .and(TaskSpecifications.assignedToUser(assignedToUserId));

        return taskRepository.findAll(spec, pageable);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional
    public Task updateTask(Long taskId, TaskRequest request) {
        Task task = getTaskById(taskId);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());

        if (request.getAssignedToUserId() != null) {
            User user = userService.getUserById(request.getAssignedToUserId());
            task.setAssignedTo(user);
        } else {
            task.setAssignedTo(null);
        }

        return task;
    }

    @Override
    @Transactional
    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = getTaskById(taskId);
        task.setStatus(status);
        return task;
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
