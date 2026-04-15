package com.company.registration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Floor {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private Company company;
}