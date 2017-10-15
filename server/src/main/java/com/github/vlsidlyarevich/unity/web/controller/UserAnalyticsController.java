package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.domain.service.UserAnalyticsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/api/v1/user/{userId}/analytics", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserAnalyticsController {

    private final UserAnalyticsService analyticsService;

    @GetMapping
    public ResponseEntity getAnalyticsByUserId(@PathVariable final String userId) {
        return ResponseEntity.ok().body(analyticsService.findByUserId(userId));
    }

    @GetMapping(value = "/{reportId}")
    public ResponseEntity getAnalyticsReportById(@PathVariable final String userId,
                                                 @PathVariable final String reportId) {
        return ResponseEntity.ok().body(analyticsService.findReportById(userId, reportId));
    }

    @DeleteMapping(value = "/{reportId}")
    public ResponseEntity deleteAnalyticsReportById(@PathVariable final String userId,
                                                    @PathVariable final String reportId) {
        return ResponseEntity.ok().body(analyticsService.deleteReport(userId, reportId));
    }

    @DeleteMapping(value = "/all")
    public ResponseEntity deleteAllAnalyticsReports(@PathVariable final String userId) {
        return ResponseEntity.ok().body(analyticsService.deleteAllReports(userId));
    }
}
