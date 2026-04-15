package com.company.registration.repository;

import com.company.registration.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByHandledById(Long userId);

    List<Visit> findByDepartmentId(Long departmentId);

    List<Visit> findByCheckInBetween(LocalDateTime start, LocalDateTime end);

    @Query("""
        SELECT v FROM Visit v
        WHERE v.checkOut IS NULL
    """)
    List<Visit> findActiveVisits();
}
