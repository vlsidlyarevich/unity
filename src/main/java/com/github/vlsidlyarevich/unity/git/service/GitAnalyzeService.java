package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;

import java.util.Optional;

public interface GitAnalyzeService {

    Optional<AnalysisReport> analyze(String gitLogin);
}
