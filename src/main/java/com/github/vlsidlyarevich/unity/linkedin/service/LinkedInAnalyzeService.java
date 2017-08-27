package com.github.vlsidlyarevich.unity.linkedin.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;

import java.util.Optional;

public interface LinkedInAnalyzeService {

    Optional<AnalysisReport> analyze(String userUrl);
}
