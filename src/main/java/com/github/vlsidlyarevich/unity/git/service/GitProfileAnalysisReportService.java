package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;

public interface GitProfileAnalysisReportService {

    AnalysisReport getAnalysisReport(String username);
}
