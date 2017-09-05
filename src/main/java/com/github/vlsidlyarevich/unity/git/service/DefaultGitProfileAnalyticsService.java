package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;
import com.github.vlsidlyarevich.unity.domain.exception.ResourceNotFoundException;
import com.github.vlsidlyarevich.unity.domain.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultGitProfileAnalyticsService implements GitProfileAnalyticsService {

    private final GitAnalyzeService gitAnalyzeService;
    private final UserAnalyticsService userAnalyticsService;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public UserAnalytics getGitProfileAnalytics(final String username) {
        final Optional<AnalysisReport> report = gitAnalyzeService.analyze(username);

        return report.map(this::createAndSaveUserAnalytics)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Git profile data with login:%s not found", username)));
    }

    private UserAnalytics createAndSaveUserAnalytics(final AnalysisReport report) {
        final List<AnalysisReport> reports = new ArrayList<>();
        reports.add(report);

        final String userId = ((User)
                authenticationFacade.getAuthentication().getDetails()).getId();

        final UserAnalytics userAnalytics = new UserAnalytics(userId, reports);
        userAnalyticsService.add(userAnalytics);

        return userAnalytics;
    }
}
