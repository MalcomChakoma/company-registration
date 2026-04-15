package com.company.registration.dto;

import lombok.Data;

@Data
public class VisitRequest {
    private String visitorName;
    private Long departmentId;
    private Long userId;
}
