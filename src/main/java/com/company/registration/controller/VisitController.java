package com.company.registration.controller;

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

    @PostMapping("/checkin")
    public Visit checkIn(@RequestParam String visitorName,
                         @RequestParam Long departmentId,
                         @RequestParam Long userId) {

        return visitService.checkIn(visitorName, departmentId, userId);
    }

    @PostMapping("/checkout/{visitId}")
    public Visit checkOut(@PathVariable Long visitId) {
        return visitService.checkOut(visitId);
    }

    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }

    @GetMapping("/active")
    public List<Visit> getActiveVisits() {
        return visitService.getActiveVisits();
    }

    @GetMapping("/user/{userId}")
    public List<Visit> getVisitsByUser(@PathVariable Long userId) {
        return visitService.getVisitsByUser(userId);
    }
}