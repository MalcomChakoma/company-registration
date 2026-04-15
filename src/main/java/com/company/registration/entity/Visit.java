package com.company.registration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Visit {

    @Id
    @GeneratedValue
    private Long id;

    private String visitorName;

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    @ManyToOne
    private Department department;

    @ManyToOne
    private User handledBy;
}