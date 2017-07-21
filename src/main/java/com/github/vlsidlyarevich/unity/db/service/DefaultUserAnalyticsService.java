package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.exception.ResourceNotFoundException;
import com.github.vlsidlyarevich.unity.db.repository.UserAnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultUserAnalyticsService implements UserAnalyticsService {

    private final UserAnalyticsRepository repository;

    @Autowired
    public DefaultUserAnalyticsService(final UserAnalyticsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAnalytics add(final UserAnalytics userAnalytics) {
        return Optional.ofNullable(userAnalytics)
                .map(analytics -> {

                    final Optional<UserAnalytics> savedAnalytics
                            = Optional.ofNullable(repository
                            .findByUserId(userAnalytics.getUserId()));

                    savedAnalytics.ifPresent(savedAnalyt ->
                            userAnalytics.getReports().addAll(savedAnalyt.getReports())
                    );

                    return repository.save(analytics);
                }).orElseThrow(()
                        -> new IllegalArgumentException("User analytics should not be empty"));
    }

    @Override
    public UserAnalytics find(final String id) {
        return Optional.ofNullable(repository.findOne(id))
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("User Analytics with id:%s not found", id)));
    }

    @Override
    public AnalysisReport findReportById(final String userId, final String reportId) {
        final UserAnalytics analytics = findByUserId(userId);

        return Optional.ofNullable(analytics.getReports()
                .stream()
                .filter(analysisReport -> Objects.equals(analysisReport.getId(), reportId))
                .findFirst()
                .orElse(null))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Analytics report with id:%s of user with id:%s not found",
                                reportId, userId)));

    }

    @Override
    public UserAnalytics findByUserId(final String userId) {
        return Optional.ofNullable(repository.findByUserId(userId))
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("User Analytics with user id:%s not found", userId)));
    }

    @Override
    public List<UserAnalytics> findAll() {
        return repository.findAll();
    }

    @Override
    public String delete(final String id) {
        repository.delete(id);
        return id;
    }

    @Override
    public String deleteReport(final String userId, final String reportId) {
        final Optional<UserAnalytics> analytics
                = Optional.ofNullable(repository.findByUserId(userId));

        analytics.map(analyt -> {
            analyt.getReports()
                    .removeIf(analysisReport -> analysisReport.getId().equals(reportId));

            return repository.save(analyt);
        }).orElseThrow(() -> new ResourceNotFoundException(String
                .format("User Analytics with user id:%s not found", userId)));

        return reportId;
    }

    @Override
    public List<String> deleteAll() {
        final List<String> result = new ArrayList<>();
        repository.findAll()
                .forEach(userAnalytics -> result.add(userAnalytics.getId()));

        repository.deleteAll();

        return result;
    }

    @Override
    public List<String> deleteAllReports(final String userId) {
        final Optional<UserAnalytics> analytics
                = Optional.ofNullable(repository.findByUserId(userId));
        final List<String> result = new ArrayList<>();

        analytics.map(analyt -> {
            analyt.getReports()
                    .forEach(analysisReport -> result.add(analysisReport.getId()));
            analyt.getReports().clear();

            return repository.save(analyt);
        }).orElseThrow(() -> new ResourceNotFoundException(String
                .format("User Analytics with user id:%s not found", userId)));

        return result;
    }
}
