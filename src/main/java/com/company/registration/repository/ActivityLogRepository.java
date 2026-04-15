package com.company.registration.repository;

import com.company.registration.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    List<ActivityLog> findByUserId(Long userId);

    List<ActivityLog> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

    @Query("""
        SELECT a FROM ActivityLog a
        WHERE a.user.id = :userId
        ORDER BY a.timestamp DESC
    """)
    List<ActivityLog> findRecentActivitiesByUser(@Param("userId") Long userId);
}
