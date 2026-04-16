package com.company.registration.controller;

import com.company.registration.entity.Company;
import com.company.registration.entity.Department;
import com.company.registration.entity.Floor;
import com.company.registration.service.AdminService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // ================= COMPANY =================

    // ✅ CREATE COMPANY
    @PostMapping("/company")
    public Company createCompany(@RequestBody Company company) {
        return adminService.createCompany(company);
    }

    // ✅ GET ALL COMPANIES
    @GetMapping("/company")
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    // ✅ DELETE COMPANY
    @DeleteMapping("/company/{id}")
    public void deleteCompany(@PathVariable Long id) {
        adminService.deleteCompany(id);
    }

    // ================= DEPARTMENT =================

    // ✅ CREATE DEPARTMENT
    @PostMapping("/department")
    public Department createDepartment(@RequestBody Department department) {
        return adminService.createDepartment(department);
    }

    // ✅ GET DEPARTMENTS BY COMPANY
    @GetMapping("/department/{companyId}")
    public List<Department> getDepartmentsByCompany(@PathVariable Long companyId) {
        return adminService.getDepartmentsByCompany(companyId);
    }

    // ✅ DELETE DEPARTMENT
    @DeleteMapping("/department/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        adminService.deleteDepartment(id);
    }

    // ================= FLOOR =================

    // ✅ CREATE FLOOR
    @PostMapping("/floor")
    public Floor createFloor(@RequestBody Floor floor) {
        return adminService.createFloor(floor);
    }

    // ✅ GET FLOORS BY COMPANY
    @GetMapping("/floor/{companyId}")
    public List<Floor> getFloorsByCompany(@PathVariable Long companyId) {
        return adminService.getFloorsByCompany(companyId);
    }

    // ✅ DELETE FLOOR
    @DeleteMapping("/floor/{id}")
    public void deleteFloor(@PathVariable Long id) {
        adminService.deleteFloor(id);
    }

    // ================= USER MANAGEMENT =================

    // ✅ ASSIGN USER TO DEPARTMENT
    @PutMapping("/user/{userId}/department/{deptId}")
    public void assignUserToDepartment(@PathVariable Long userId,
                                       @PathVariable Long deptId) {
        adminService.assignUserToDepartment(userId, deptId);
    }
}