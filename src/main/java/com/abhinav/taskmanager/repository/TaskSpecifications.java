package com.abhinav.taskmanager.repository;

import com.abhinav.taskmanager.entity.Task;
import com.abhinav.taskmanager.entity.TaskPriority;
import com.abhinav.taskmanager.entity.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {

    public static Specification<Task> hasStatus(TaskStatus status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Task> hasPriority(TaskPriority priority) {
        return (root, query, cb) ->
                priority == null ? null : cb.equal(root.get("priority"), priority);
    }

    public static Specification<Task> assignedToUser(Long userId) {
        return (root, query, cb) ->
                userId == null ? null : cb.equal(root.get("assignedTo").get("id"), userId);
    }
}
