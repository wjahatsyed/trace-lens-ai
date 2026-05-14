package com.tracelens.backend.dto;

public record ParsedLogEntry(
        String timestamp,
        String level,
        String service,
        String message
) {
}