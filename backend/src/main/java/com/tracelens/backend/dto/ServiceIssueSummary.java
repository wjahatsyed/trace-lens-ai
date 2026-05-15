package com.tracelens.backend.dto;

public record ServiceIssueSummary(
        String service,
        long errorCount,
        long warningCount,
        long totalIssues
) {
}