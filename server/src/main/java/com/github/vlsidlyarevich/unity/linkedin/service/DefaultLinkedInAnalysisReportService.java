package com.github.vlsidlyarevich.unity.linkedin.service;

import com.github.vlsidlyarevich.unity.common.service.AbstractAnalysisReportService;
import com.github.vlsidlyarevich.unity.domain.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultLinkedInAnalysisReportService extends AbstractAnalysisReportService<LinkedInAnalyzeService>
        implements LinkedInAnalysisReportService {

    @Autowired
    public DefaultLinkedInAnalysisReportService(final UserAnalyticsService userAnalyticsService,
                                                final AuthenticationFacade authenticationFacade,
                                                final LinkedInAnalyzeService analyzeService) {
        super(userAnalyticsService, authenticationFacade, analyzeService);
    }
}
