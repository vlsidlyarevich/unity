package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.db.model.User;
import com.github.vlsidlyarevich.unity.db.model.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.git.model.GitProfileData;
import com.github.vlsidlyarevich.unity.git.service.GitProfileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/git/profile")
public class GitProfileController {

    @Autowired
    private GitProfileDataService gitProfileDataService;

    @Autowired
    private UserAnalyticsService userAnalyticsService;

    @RequestMapping(value = "{gitProfile}", method = RequestMethod.GET)
    public ResponseEntity getGitData(@PathVariable String gitProfile) {
        Optional<GitProfileData> result = gitProfileDataService.getGitProfileData(gitProfile);
        if (!result.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        UserAnalytics userAnalytics = new UserAnalytics();
        userAnalytics.setAnalyzedData(new ArrayList() {{
            add(result.get());
        }});
        userAnalytics.setUserId(((User) SecurityContextHolder.getContext().getAuthentication().getDetails()).getId());
        userAnalyticsService.add(userAnalytics);
        return new ResponseEntity(result.get(), HttpStatus.OK);
    }
}
