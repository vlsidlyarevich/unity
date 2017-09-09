package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.common.service.AbstractAnalysisReportService;
import com.github.vlsidlyarevich.unity.domain.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTwitterAnalysisReportService extends AbstractAnalysisReportService<TwitterAnalyzeService>
        implements TwitterAnalysisReportService {

    @Autowired
    public DefaultTwitterAnalysisReportService(final UserAnalyticsService userAnalyticsService,
                                               final AuthenticationFacade authenticationFacade,
                                               final TwitterAnalyzeService analyzeService) {
        super(userAnalyticsService, authenticationFacade, analyzeService);
    }
}
