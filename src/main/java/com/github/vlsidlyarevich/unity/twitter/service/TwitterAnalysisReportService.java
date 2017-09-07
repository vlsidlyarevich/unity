package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;

public interface TwitterAnalysisReportService {

    AnalysisReport getTwitterProfileAnalysisReport(String username);
}
