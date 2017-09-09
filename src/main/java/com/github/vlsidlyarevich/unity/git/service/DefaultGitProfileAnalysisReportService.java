package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.common.service.AbstractAnalysisReportService;
import com.github.vlsidlyarevich.unity.domain.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultGitProfileAnalysisReportService extends AbstractAnalysisReportService<GitAnalyzeService>
        implements GitProfileAnalysisReportService {

    @Autowired
    public DefaultGitProfileAnalysisReportService(final UserAnalyticsService userAnalyticsService,
                                                  final AuthenticationFacade authenticationFacade,
                                                  final GitAnalyzeService analyzeService) {
        super(userAnalyticsService, authenticationFacade, analyzeService);
    }
}
