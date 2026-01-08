package com.abhinav.taskmanager.controller;

import com.abhinav.taskmanager.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final String API_KEY = "SECRET_API_KEY_123";
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestHeader("X-API-KEY") String apiKey) {

        if (!API_KEY.equals(apiKey)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API key");
        }

        return Map.of("token", jwtUtil.generateToken("api-client"));
    }
}
