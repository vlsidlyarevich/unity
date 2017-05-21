package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.git.service.GitAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/git/profile")
public class GitProfileController {

    private final GitAnalyzeService gitAnalyzeService;

    private final UserAnalyticsService userAnalyticsService;

    @Autowired
    public GitProfileController(final GitAnalyzeService gitAnalyzeService,
                                final UserAnalyticsService userAnalyticsService) {
        this.gitAnalyzeService = gitAnalyzeService;
        this.userAnalyticsService = userAnalyticsService;
    }

    @RequestMapping(value = "{gitLogin}", method = RequestMethod.GET)
    public ResponseEntity getGitData(@PathVariable final String gitLogin) {
        Optional<AnalysisReport> report = gitAnalyzeService.analyze(gitLogin);
        if (!report.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        UserAnalytics userAnalytics = new UserAnalytics();

        List<AnalysisReport> reports = new ArrayList<>();
        reports.add(report.get());

        userAnalytics.setReports(reports);

        userAnalytics.setUserId(((User) SecurityContextHolder
                .getContext().getAuthentication().getDetails()).getId());
        userAnalytics.setCreatedAt(String.valueOf(LocalDateTime.now()));
        userAnalyticsService.add(userAnalytics);
        return new ResponseEntity(userAnalytics, HttpStatus.OK);
    }
}
