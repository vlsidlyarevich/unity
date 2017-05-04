package com.github.vlsidlyarevich.unity.git.service.impl;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.git.model.GitProfileData;
import com.github.vlsidlyarevich.unity.git.service.GitAnalyzeService;
import com.github.vlsidlyarevich.unity.git.service.GitProfileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class SimpleGitAnalyzeReportService implements GitAnalyzeService {

    @Autowired
    private GitProfileDataService gitProfileDataService;

    @Override
    public Optional<AnalysisReport> analyze(String gitLogin) {
        LocalDateTime startDate = LocalDateTime.now();
        Optional<GitProfileData> gitProfileData = gitProfileDataService.getGitProfileData(gitLogin);
        LocalDateTime endDate = LocalDateTime.now();
        Optional<AnalysisReport> report = Optional.empty();

        if (gitProfileData.isPresent()) {
            Date analyzedAt = Date.from(Instant.from(LocalDateTime.now()));
            Long analyzeTime = ChronoUnit.SECONDS.between(startDate, endDate);
            report = Optional.of(new AnalysisReport(gitProfileData.get(), analyzedAt, analyzeTime));
        }

        return report;
    }
}
