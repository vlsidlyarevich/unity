package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.linkedin.service.LinkedInAnalyticsService;
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
@RequestMapping(path = "/api/v1/linkedin/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class LinkedinProfileAnalyticsController {

    private final LinkedInAnalyticsService service;

    @RequestMapping(value = "/{userUrl}", method = RequestMethod.GET)
    public ResponseEntity getLinkedInDataByUserUrl(@PathVariable final String userUrl) {
        return ResponseEntity.ok(service.getLinkedInProfileAnalytics(userUrl));
    }
}
