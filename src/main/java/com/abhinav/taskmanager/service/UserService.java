package com.abhinav.taskmanager.service;

import com.abhinav.taskmanager.dto.CreateUserRequest;
import com.abhinav.taskmanager.entity.User;

public interface UserService {
    public User createUser(CreateUserRequest request);
}