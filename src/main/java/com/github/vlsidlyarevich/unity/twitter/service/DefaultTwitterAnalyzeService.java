package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.twitter.model.TwitterProfileData;
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
public class DefaultTwitterAnalyzeService implements TwitterAnalyzeService {

    private final TwitterProfileDataService profileDataService;

    @Override
    public Optional<AnalysisReport> analyze(final String username) {
        final LocalDateTime startDate = LocalDateTime.now();

        final Optional<TwitterProfileData> twitterProfileData
                = profileDataService.getTwitterProfileData(username);

        final LocalDateTime endDate = LocalDateTime.now();
        Optional<AnalysisReport> report = Optional.empty();

        if (twitterProfileData.isPresent()) {
            final Date analyzedAt
                    = Date.from(Instant.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
            final Long analyzeTime = ChronoUnit.SECONDS.between(startDate, endDate);

            report = Optional.of(new AnalysisReport(twitterProfileData.get(), analyzedAt, analyzeTime));
        }

        return report;
    }
}
