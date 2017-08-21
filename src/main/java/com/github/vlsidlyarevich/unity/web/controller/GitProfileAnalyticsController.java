package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.git.service.GitProfileAnalyticsService;
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
@RequestMapping(path = "/api/v1/git/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class GitProfileAnalyticsController {

    private final GitProfileAnalyticsService service;

    @RequestMapping(value = "/{gitLogin}", method = RequestMethod.GET)
    public ResponseEntity getGitData(@PathVariable final String gitLogin) {
        return ResponseEntity.ok(service.getGitProfileAnalytics(gitLogin));
    }
}
