package com.github.vlsidlyarevich.unity.common.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.domain.exception.ResourceNotFoundException;
import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;
import com.github.vlsidlyarevich.unity.domain.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class AbstractAnalysisReportService<T extends AnalyzeService> {

    private final UserAnalyticsService userAnalyticsService;
    private final AuthenticationFacade authenticationFacade;
    private final T analyzeService;

    public AnalysisReport getAnalysisReport(final String username) {
        final Optional<AnalysisReport> report = analyzeService.analyze(username);

        //FIXME add service type
        return report.map(this::createAndSaveUserAnalytics)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Profile with url:%s not found", username)));
    }

    private AnalysisReport createAndSaveUserAnalytics(final AnalysisReport report) {
        final List<AnalysisReport> reports = new ArrayList<>();
        reports.add(report);

        final String userId = ((User)
                authenticationFacade.getAuthentication().getDetails()).getId();

        final UserAnalytics userAnalytics = new UserAnalytics(userId, reports);
        userAnalyticsService.add(userAnalytics);

        return report;
    }
}
