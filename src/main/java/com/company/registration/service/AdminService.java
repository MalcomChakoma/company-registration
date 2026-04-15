package com.company.registration.service;

import com.company.registration.entity.Company;
import com.company.registration.entity.Department;
import com.company.registration.entity.Floor;
import com.company.registration.entity.User;
import com.company.registration.exception.BadRequestException;
import com.company.registration.exception.ResourceNotFoundException;
import com.company.registration.repository.CompanyRepository;
import com.company.registration.repository.DepartmentRepository;
import com.company.registration.repository.FloorRepository;
import com.company.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final CompanyRepository companyRepo;
    private final DepartmentRepository deptRepo;
    private final FloorRepository floorRepo;
    private final UserRepository userRepo;

    // ================= COMPANY =================

    public Company createCompany(Company company) {
        return companyRepo.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    public Company getCompany(Long id) {
        return companyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }

    public void deleteCompany(Long id) {
        if (!companyRepo.existsById(id)) {
            throw new ResourceNotFoundException("Company not found");
        }
        companyRepo.deleteById(id);
    }

    // ================= DEPARTMENT =================

    public Department createDepartment(Department dept) {
        if (dept.getCompany() == null) {
            throw new BadRequestException("Department must belong to a company");
        }
        return deptRepo.save(dept);
    }

    public List<Department> getDepartmentsByCompany(Long companyId) {
        return deptRepo.findAll()
                .stream()
                .filter(d -> d.getCompany().getId().equals(companyId))
                .toList();
    }

    public Department getDepartment(Long id) {
        return deptRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }

    public void deleteDepartment(Long id) {
        if (!deptRepo.existsById(id)) {
            throw new ResourceNotFoundException("Department not found");
        }
        deptRepo.deleteById(id);
    }

    // ================= FLOOR =================

    public Floor createFloor(Floor floor) {
        if (floor.getCompany() == null) {
            throw new BadRequestException("Floor must belong to a company");
        }
        return floorRepo.save(floor);
    }

    public List<Floor> getFloorsByCompany(Long companyId) {
        return floorRepo.findAll()
                .stream()
                .filter(f -> f.getCompany().getId().equals(companyId))
                .toList();
    }

    public void deleteFloor(Long id) {
        if (!floorRepo.existsById(id)) {
            throw new ResourceNotFoundException("Floor not found");
        }
        floorRepo.deleteById(id);
    }

    // ================= USER =================

    public User assignUserToDepartment(Long userId, Long departmentId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Department dept = deptRepo.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        user.setDepartment(dept);

        return userRepo.save(user);
    }
}