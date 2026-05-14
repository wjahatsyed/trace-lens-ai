package com.tracelens.backend.dto;

public record HealthResponse(
        String status,
        String service
) {
}