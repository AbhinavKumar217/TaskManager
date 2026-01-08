package com.abhinav.taskmanager.service.impl;

import com.abhinav.taskmanager.dto.CreateTaskRequest;
import com.abhinav.taskmanager.entity.Task;
import com.abhinav.taskmanager.entity.TaskPriority;
import com.abhinav.taskmanager.entity.TaskStatus;
import com.abhinav.taskmanager.entity.User;
import com.abhinav.taskmanager.exception.ResourceNotFoundException;
import com.abhinav.taskmanager.repository.TaskRepository;
import com.abhinav.taskmanager.repository.TaskSpecifications;
import com.abhinav.taskmanager.repository.UserRepository;
import com.abhinav.taskmanager.service.TaskService;
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

}
