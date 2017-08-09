package com.github.vlsidlyarevich.unity.web.controller.api;

import com.github.vlsidlyarevich.unity.git.service.GitProfileAnalyticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/git/profile")
public class GitProfileAnalyticsController {

    private final GitProfileAnalyticsService service;

    @RequestMapping(value = "/{gitLogin}", method = RequestMethod.GET)
    public ResponseEntity getGitData(@PathVariable final String gitLogin) {
        return new ResponseEntity<>(service.getGitProfileAnalytics(gitLogin), HttpStatus.OK);
    }
}