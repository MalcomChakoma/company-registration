package com.company.registration.controller;

import com.company.registration.dto.AuthResponse;
import com.company.registration.dto.LoginRequest;
import com.company.registration.dto.RegisterRequest;
import com.company.registration.entity.User;
import com.company.registration.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }
}