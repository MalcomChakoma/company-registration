package com.company.registration.service;

import com.company.registration.config.JwtUtil;
import com.company.registration.dto.AuthResponse;
import com.company.registration.dto.LoginRequest;
import com.company.registration.dto.RegisterRequest;
import com.company.registration.entity.ActivityLog;
import com.company.registration.entity.Role;
import com.company.registration.entity.User;
import com.company.registration.exception.BadRequestException;
import com.company.registration.repository.ActivityLogRepository;
import com.company.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ActivityLogRepository activityLogRepository;

    // 🔐 LOGIN
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("USER: " + user);
        System.out.println("ROLE FROM DB: " + user.getRole());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);

        System.out.println("GENERATED TOKEN: " + token);

        return new AuthResponse(token, user.getRole().name());
    }

    // 📝 REGISTER
    public User register(RegisterRequest request) {

        if (request.getUsername() == null || request.getPassword() == null) {
            throw new BadRequestException("Username and password are required");
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // ✅ Default role (change if needed)
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        // ✅ Log registration
        logActivity(savedUser, "REGISTER");

        return savedUser;
    }

    // 📊 ACTIVITY LOGGING
    private void logActivity(User user, String action) {
        ActivityLog log = new ActivityLog();
        log.setUser(user);
        log.setAction(action);
        log.setTimestamp(LocalDateTime.now());
        activityLogRepository.save(log);
    }
}