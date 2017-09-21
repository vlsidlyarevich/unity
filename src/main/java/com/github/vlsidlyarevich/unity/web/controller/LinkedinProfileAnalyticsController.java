package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.linkedin.service.LinkedInAnalysisReportService;
import com.github.vlsidlyarevich.unity.web.dto.linkedin.LinkedInAnalyzeProfileRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/api/v1/linkedin/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class LinkedinProfileAnalyticsController {

    private final LinkedInAnalysisReportService analyticsService;

    @PostMapping
    public ResponseEntity getLinkedInDataByUserUrl(@RequestBody final LinkedInAnalyzeProfileRequest request) {
        return ResponseEntity.ok(analyticsService.getAnalysisReport(request.getPublicUserUrl()));
    }
}
