package com.tracelens.backend.controller;

import com.tracelens.backend.dto.LogAnalysisResponse;
import com.tracelens.backend.dto.LogUploadResponse;
import com.tracelens.backend.dto.ParsedLogEntry;
import com.tracelens.backend.dto.ServiceIssueSummary;
import com.tracelens.backend.service.LogParserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/logs")
public class LogUploadController {

    private final LogParserService logParserService;

    public LogUploadController(LogParserService logParserService) {
        this.logParserService = logParserService;
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public LogUploadResponse uploadLogFile(@RequestParam("file") MultipartFile file) {
        return new LogUploadResponse(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                "Log file received successfully"
        );
    }

    @PostMapping(
            value = "/analyze",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public LogAnalysisResponse analyzeLogFile(@RequestParam("file") MultipartFile file) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        List<String> lines = content.lines().toList();
        List<ParsedLogEntry> entries = logParserService.parse(lines);

        Map<String, Long> levelCounts = entries.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        ParsedLogEntry::level,
                        java.util.stream.Collectors.counting()
                ));

        Set<String> affectedServices = entries.stream()
                .filter(entry -> "ERROR".equalsIgnoreCase(entry.level()) || "WARN".equalsIgnoreCase(entry.level()))
                .map(ParsedLogEntry::service)
                .collect(java.util.stream.Collectors.toCollection(java.util.TreeSet::new));

        List<ServiceIssueSummary> suspiciousServices = affectedServices.stream()
                .map(service -> {
                    long errorCount = entries.stream()
                            .filter(entry -> service.equals(entry.service()))
                            .filter(entry -> "ERROR".equalsIgnoreCase(entry.level()))
                            .count();

                    long warningCount = entries.stream()
                            .filter(entry -> service.equals(entry.service()))
                            .filter(entry -> "WARN".equalsIgnoreCase(entry.level()))
                            .count();

                    return new ServiceIssueSummary(
                            service,
                            errorCount,
                            warningCount,
                            errorCount + warningCount
                    );
                })
                .sorted(java.util.Comparator.comparing(ServiceIssueSummary::totalIssues).reversed())
                .toList();

        return new LogAnalysisResponse(
                file.getOriginalFilename(),
                lines.size(),
                entries.size(),
                levelCounts,
                affectedServices,
                suspiciousServices,
                entries
        );
    }
}