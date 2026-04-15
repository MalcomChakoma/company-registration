package com.company.registration.service;

import com.company.registration.entity.ActivityLog;
import com.company.registration.entity.Department;
import com.company.registration.entity.User;
import com.company.registration.entity.Visit;
import com.company.registration.exception.BadRequestException;
import com.company.registration.exception.ResourceNotFoundException;
import com.company.registration.repository.ActivityLogRepository;
import com.company.registration.repository.DepartmentRepository;
import com.company.registration.repository.UserRepository;
import com.company.registration.repository.VisitRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepo;
    private final UserRepository userRepo;
    private final DepartmentRepository deptRepo;
    private final ActivityLogRepository logRepo;

    // ================= CHECK-IN =================

    public Visit checkIn(String visitorName, Long deptId, Long userId) {

        if (visitorName == null || visitorName.isBlank()) {
            throw new BadRequestException("Visitor name required");
        }

        Department dept = deptRepo.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Visit visit = new Visit();
        visit.setVisitorName(visitorName);
        visit.setDepartment(dept);
        visit.setHandledBy(user);
        visit.setCheckIn(LocalDateTime.now());

        Visit saved = visitRepo.save(visit);

        log(user, "CHECK-IN: " + visitorName);

        return saved;
    }

    // ================= CHECK-OUT =================

    public Visit checkOut(Long visitId) {

        Visit visit = visitRepo.findById(visitId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit not found"));

        if (visit.getCheckOut() != null) {
            throw new BadRequestException("Visitor already checked out");
        }

        visit.setCheckOut(LocalDateTime.now());

        Visit updated = visitRepo.save(visit);

        log(visit.getHandledBy(), "CHECK-OUT: " + visit.getVisitorName());

        return updated;
    }

    // ================= GET ALL VISITS =================

    public List<Visit> getAllVisits() {
        return visitRepo.findAll();
    }

    // ================= GET ACTIVE VISITS (FIXED METHOD) =================

    public List<Visit> getActiveVisits() {
        return visitRepo.findActiveVisits();
    }

    // ================= GET VISITS BY USER =================

    public List<Visit> getVisitsByUser(Long userId) {

        if (!userRepo.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        return visitRepo.findByHandledById(userId);
    }

    // ================= LOGGING =================

    private void log(User user, String action) {
        ActivityLog log = new ActivityLog();
        log.setUser(user);
        log.setAction(action);
        log.setTimestamp(LocalDateTime.now());
        logRepo.save(log);
    }
}