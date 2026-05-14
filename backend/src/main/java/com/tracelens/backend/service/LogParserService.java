package com.tracelens.backend.service;

import com.tracelens.backend.dto.ParsedLogEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogParserService {

    public List<ParsedLogEntry> parse(List<String> lines) {
        List<ParsedLogEntry> entries = new ArrayList<>();

        for (String line : lines) {
            ParsedLogEntry entry = parseLine(line);

            if (entry != null) {
                entries.add(entry);
            }
        }

        return entries;
    }

    private ParsedLogEntry parseLine(String line) {
        if (line == null || line.isBlank()) {
            return null;
        }

        String[] parts = line.split(" ", 4);

        if (parts.length < 4) {
            return null;
        }

        String timestamp = parts[0];
        String level = parts[1];
        String service = parts[2];
        String message = parts[3];

        return new ParsedLogEntry(timestamp, level, service, message);
    }
}