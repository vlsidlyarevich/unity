package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.git.service.GitProfileAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/git/profile")
public class GitProfileAnalyticsController {

    private final GitProfileAnalyticsService service;

    @Autowired
    public GitProfileAnalyticsController(final GitProfileAnalyticsService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{gitLogin}", method = RequestMethod.GET)
    public ResponseEntity getGitData(@PathVariable final String gitLogin) {
        return new ResponseEntity<>(service.getGitProfileAnalytics(gitLogin), HttpStatus.OK);
    }
}
