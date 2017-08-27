package com.github.vlsidlyarevich.unity.linkedin.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.linkedin.model.LinkedInProfileData;
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
public class DefaultLinkedInAnalyzeService implements LinkedInAnalyzeService {

    private final LinkedInProfileDataService profileDataService;

    @Override
    public Optional<AnalysisReport> analyze(final String userUrl) {
        final LocalDateTime startDate = LocalDateTime.now();

        final Optional<LinkedInProfileData> linkedInProfileData
                = profileDataService.getLinkedInProfileData(userUrl);

        final LocalDateTime endDate = LocalDateTime.now();
        Optional<AnalysisReport> report = Optional.empty();

        if (linkedInProfileData.isPresent()) {
            final Date analyzedAt
                    = Date.from(Instant.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
            final Long analyzeTime = ChronoUnit.SECONDS.between(startDate, endDate);

            report = Optional.of(new AnalysisReport(linkedInProfileData.get(), analyzedAt, analyzeTime));
        }

        return report;
    }
}
