package com.github.vlsidlyarevich.unity.common.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.common.model.AnalysisResult;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
public abstract class AbstractAnalyzeService<T extends DataService<D>, D extends AnalysisResult> {

    private final T service;

    public Optional<AnalysisReport> analyze(final String username) {
        final LocalDateTime startDate = LocalDateTime.now();

        final Optional<? extends D> data = service.getData(username);

        final LocalDateTime endDate = LocalDateTime.now();
        Optional<AnalysisReport> report = Optional.empty();

        if (data.isPresent()) {
            final Date analyzedAt
                    = Date.from(Instant.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
            final Long analyzeTime = ChronoUnit.SECONDS.between(startDate, endDate);

            report = Optional.of(new AnalysisReport(data.get(), analyzedAt, analyzeTime));
        }

        return report;
    }
}
