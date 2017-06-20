package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.git.service.GitAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/git/profile")
public class GitProfileController {

    private final GitAnalyzeService gitAnalyzeService;

    private final UserAnalyticsService userAnalyticsService;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public GitProfileController(final GitAnalyzeService gitAnalyzeService,
                                final UserAnalyticsService userAnalyticsService,
                                final AuthenticationFacade authenticationFacade) {
        this.gitAnalyzeService = gitAnalyzeService;
        this.userAnalyticsService = userAnalyticsService;
        this.authenticationFacade = authenticationFacade;
    }

    @RequestMapping(value = "/{gitLogin}", method = RequestMethod.GET)
    public ResponseEntity getGitData(@PathVariable final String gitLogin) {
        Optional<AnalysisReport> report = gitAnalyzeService.analyze(gitLogin);
        if (!report.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<AnalysisReport> reports = new ArrayList<>();
        reports.add(report.get());

        String userId = ((User)
                authenticationFacade.getAuthentication().getDetails()).getId();

        UserAnalytics userAnalytics = new UserAnalytics(userId, reports);
        userAnalytics.setCreatedAt(String.valueOf(LocalDateTime.now()));
        userAnalyticsService.add(userAnalytics);
        return new ResponseEntity<>(userAnalytics, HttpStatus.OK);
    }
}
