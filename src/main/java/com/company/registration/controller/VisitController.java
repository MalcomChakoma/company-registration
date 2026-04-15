package com.company.registration.controller;

import com.company.registration.dto.VisitRequest;
import com.company.registration.entity.Visit;
import com.company.registration.service.VisitService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visit")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    // ================= CHECK-IN =================

    @PostMapping("/checkin")
    public Visit checkIn(@RequestBody VisitRequest request) {
        return visitService.checkIn(
                request.getVisitorName(),
                request.getDepartmentId(),
                request.getUserId()
        );
    }

    // ================= CHECK-OUT =================

    @PostMapping("/checkout/{visitId}")
    public Visit checkOut(@PathVariable Long visitId) {
        return visitService.checkOut(visitId);
    }

    // ================= GET ALL VISITS =================

    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }

    // ================= ACTIVE VISITS =================

    @GetMapping("/active")
    public List<Visit> getActiveVisits() {
        return visitService.getActiveVisits();
    }

    // ================= VISITS BY USER =================

    @GetMapping("/user/{userId}")
    public List<Visit> getVisitsByUser(@PathVariable Long userId) {
        return visitService.getVisitsByUser(userId);
    }
}