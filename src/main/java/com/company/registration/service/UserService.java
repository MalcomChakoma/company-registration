package com.company.registration.service;

import com.company.registration.entity.ActivityLog;
import com.company.registration.entity.User;
import com.company.registration.entity.Visit;
import com.company.registration.exception.ResourceNotFoundException;
import com.company.registration.repository.ActivityLogRepository;
import com.company.registration.repository.UserRepository;
import com.company.registration.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final ActivityLogRepository logRepo;
    private final VisitRepository visitRepo;

    public User getUser(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<ActivityLog> getUserActivities(Long userId) {

        if (!userRepo.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        return logRepo.findAll()
                .stream()
                .filter(log -> log.getUser().getId().equals(userId))
                .toList();
    }

    public List<Visit> getUserVisits(Long userId) {

        if (!userRepo.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        return visitRepo.findByHandledById(userId);
    }
}