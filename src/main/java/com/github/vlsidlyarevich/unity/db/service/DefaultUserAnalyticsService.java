package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.repository.UserAnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
                            = Optional.ofNullable(repository.findByUserId(userAnalytics.getUserId()));

                    savedAnalytics.ifPresent(savedAnalyt ->
                            userAnalytics.getReports().addAll(savedAnalyt.getReports())
                    );

                    return repository.save(analytics);
                }).orElseThrow(() -> new IllegalArgumentException("User analytics should not be empty"));
    }

    @Override
    public UserAnalytics find(final String id) {
        return repository.findOne(id);
    }

    @Override
    public UserAnalytics findByUserId(final String userId) {
        return repository.findByUserId(userId);
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
        });

        return reportId;
    }

    @Override
    public List<String> deleteAll() {
        List<String> result = new ArrayList<>();
        repository.findAll()
                .forEach(userAnalytics -> result.add(userAnalytics.getId()));

        repository.deleteAll();

        return result;
    }

    @Override
    public List<String> deleteAllReports(final String userId) {
        final Optional<UserAnalytics> analytics
                = Optional.ofNullable(repository.findByUserId(userId));
        List<String> result = new ArrayList<>();

        analytics.ifPresent(analyt -> {
            analyt.getReports()
                    .forEach(analysisReport -> result.add(analysisReport.getId()));
            analyt.getReports().clear();

            repository.save(analyt);
        });

        return result;
    }
}
