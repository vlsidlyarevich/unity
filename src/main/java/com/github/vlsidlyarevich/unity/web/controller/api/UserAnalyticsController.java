package com.github.vlsidlyarevich.unity.web.controller.api;

import com.github.vlsidlyarevich.unity.db.service.UserAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/{userId}/analytics")
public class UserAnalyticsController {

    private final UserAnalyticsService service;

    @Autowired
    public UserAnalyticsController(final UserAnalyticsService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAnalyticsByUserId(@PathVariable final String userId) {
        return new ResponseEntity<>(service.findByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{reportId}", method = RequestMethod.GET)
    public ResponseEntity getAnalyticsReportById(@PathVariable final String userId,
                                                 @PathVariable final String reportId) {
        return new ResponseEntity<>(service.findReportById(userId, reportId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{reportId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAnalyticsReportById(@PathVariable final String userId,
                                                    @PathVariable final String reportId) {
        return new ResponseEntity<>(service.deleteReport(userId, reportId), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity deleteAllAnalyticsReports(@PathVariable final String userId) {
        return new ResponseEntity<>(service.deleteAllReports(userId), HttpStatus.OK);
    }
}
