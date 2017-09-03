package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;

import java.util.Optional;

public interface TwitterAnalyzeService {

    Optional<AnalysisReport> analyze(String username);
}
