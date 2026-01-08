package com.abhinav.taskmanager.service;

import com.abhinav.taskmanager.dto.CreateUserRequest;
import com.abhinav.taskmanager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User createUser(CreateUserRequest request);

    Page<User> getAllUsers(Pageable pageable);

    User getUserById(Long id);
}