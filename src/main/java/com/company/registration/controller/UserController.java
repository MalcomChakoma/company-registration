package com.company.registration.controller;

import com.company.registration.entity.ActivityLog;
import com.company.registration.entity.User;
import com.company.registration.entity.Visit;
import com.company.registration.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ================= USER PROFILE =================

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // ================= USER ACTIVITIES =================

    @GetMapping("/{id}/activities")
    public List<ActivityLog> getUserActivities(@PathVariable Long id) {
        return userService.getUserActivities(id);
    }

    // ================= USER VISITS =================

    @GetMapping("/{id}/visits")
    public List<Visit> getUserVisits(@PathVariable Long id) {
        return userService.getUserVisits(id);
    }
}