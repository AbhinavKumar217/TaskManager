package com.abhinav.taskmanager.service.impl;

import com.abhinav.taskmanager.dto.CreateUserRequest;
import com.abhinav.taskmanager.entity.User;
import com.abhinav.taskmanager.exception.DuplicateResourceException;
import com.abhinav.taskmanager.repository.UserRepository;
import com.abhinav.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);    }
}
