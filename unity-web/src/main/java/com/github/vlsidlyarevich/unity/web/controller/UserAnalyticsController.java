package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.db.model.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.service.UserAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/{userId}/analytics")
public class UserAnalyticsController {

    @Autowired
    private UserAnalyticsService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAnalyticsByUserId(@PathVariable String userId) {
        UserAnalytics analytics = service.findByUserId(userId);
        if (analytics == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(analytics, HttpStatus.OK);
    }

    @RequestMapping(value = "/{reportId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserAnalyticsReportById(@PathVariable String userId, @PathVariable String reportId) {
        return new ResponseEntity<>(service.deleteReport(userId, reportId), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllUserAnalyticsReports(@PathVariable String userId) {
        return new ResponseEntity<>(service.deleteAllReports(userId), HttpStatus.OK);
    }
}
