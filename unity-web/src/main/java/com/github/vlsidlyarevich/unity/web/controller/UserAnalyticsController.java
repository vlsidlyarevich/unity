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
@RequestMapping("/api/user/{id}/analytics")
public class UserAnalyticsController {

    @Autowired
    private UserAnalyticsService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAnalyticsByUserId(@PathVariable String id) {
        UserAnalytics analytics = service.findByUserId(id);
        if (analytics == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(analytics, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserAnalyticsById(@PathVariable String id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @RequestMapping(value = "all", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllUserAnalyticsById(@PathVariable String id) {
        return new ResponseEntity<>(service.deleteAll(), HttpStatus.OK);
    }
}
