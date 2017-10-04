package com.github.vlsidlyarevich.unity.common.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;

import java.util.Optional;

public interface AnalyzeService {

    Optional<AnalysisReport> analyze(String username);
}
