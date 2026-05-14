package com.tracelens.backend.dto;

public record LogUploadResponse(
        String fileName,
        String contentType,
        long size,
        String message
) {
}