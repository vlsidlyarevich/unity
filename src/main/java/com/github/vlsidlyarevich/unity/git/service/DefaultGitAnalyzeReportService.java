package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.git.model.GitProfileData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultGitAnalyzeReportService implements GitAnalyzeService {

    private final GitProfileDataService gitProfileDataService;

    @Override
    public Optional<AnalysisReport> analyze(final String gitLogin) {
        final LocalDateTime startDate = LocalDateTime.now();

        final Optional<GitProfileData> gitProfileData
                = gitProfileDataService.getGitProfileData(gitLogin);

        final LocalDateTime endDate = LocalDateTime.now();
        Optional<AnalysisReport> report = Optional.empty();

        if (gitProfileData.isPresent()) {
            final Date analyzedAt
                    = Date.from(Instant.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
            final Long analyzeTime = ChronoUnit.SECONDS.between(startDate, endDate);

            report = Optional.of(new AnalysisReport(gitProfileData.get(), analyzedAt, analyzeTime));
        }

        return report;
    }
}
