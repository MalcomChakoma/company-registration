package com.company.registration.controller;

import com.company.registration.entity.Company;
import com.company.registration.entity.Department;
import com.company.registration.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    @PostMapping("/company")
    public Company createCompany(@RequestBody Company c) {
        return service.createCompany(c);
    }

    @PostMapping("/department")
    public Department createDepartment(@RequestBody Department d) {
        return service.createDepartment(d);
    }
}
