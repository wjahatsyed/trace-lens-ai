package com.tracelens.backend.dto;

import java.util.List;

public record LogAnalysisResponse(
        String fileName,
        int totalLines,
        int parsedLines,
        List<ParsedLogEntry> entries
) {
}