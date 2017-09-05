package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.domain.service.UserAnalyticsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/api/v1/user/{userId}/analytics", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserAnalyticsController {

    private final UserAnalyticsService analyticsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAnalyticsByUserId(@PathVariable final String userId) {
        return ResponseEntity.ok(analyticsService.findByUserId(userId));
    }

    @RequestMapping(value = "/{reportId}", method = RequestMethod.GET)
    public ResponseEntity getAnalyticsReportById(@PathVariable final String userId,
                                                 @PathVariable final String reportId) {
        return ResponseEntity.ok(analyticsService.findReportById(userId, reportId));
    }

    @RequestMapping(value = "/{reportId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAnalyticsReportById(@PathVariable final String userId,
                                                    @PathVariable final String reportId) {
        return ResponseEntity.ok(analyticsService.deleteReport(userId, reportId));
    }

    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity deleteAllAnalyticsReports(@PathVariable final String userId) {
        return ResponseEntity.ok(analyticsService.deleteAllReports(userId));
    }
}
