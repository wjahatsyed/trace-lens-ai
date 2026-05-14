package com.tracelens.backend.controller;

import com.tracelens.backend.dto.LogUploadResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/logs")
public class LogUploadController {

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
}