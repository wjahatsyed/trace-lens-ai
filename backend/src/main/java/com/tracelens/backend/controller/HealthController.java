package com.tracelens.backend.controller;

import com.tracelens.backend.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/v1/health")
    public HealthResponse health() {
        return new HealthResponse("UP", "trace-lens-ai-backend");
    }
}