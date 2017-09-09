package com.github.vlsidlyarevich.unity.linkedin.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;

public interface LinkedInAnalysisReportService {

    AnalysisReport getAnalysisReport(String userUrl);
}
