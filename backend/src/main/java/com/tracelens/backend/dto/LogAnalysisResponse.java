package com.tracelens.backend.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public record LogAnalysisResponse(
        String fileName,
        int totalLines,
        int parsedLines,
        Map<String, Long> levelCounts,
        Set<String> affectedServices,
        List<ServiceIssueSummary> suspiciousServices,
        List<ParsedLogEntry> entries
) {
}