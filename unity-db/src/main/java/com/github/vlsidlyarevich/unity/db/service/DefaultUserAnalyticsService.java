package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.repository.UserAnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultUserAnalyticsService implements UserAnalyticsService {

    private final UserAnalyticsRepository repository;

    @Autowired
    public DefaultUserAnalyticsService(final UserAnalyticsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAnalytics add(final UserAnalytics userAnalytics) {
        final UserAnalytics analytics = repository.findByUserId(userAnalytics.getUserId());
        if (analytics != null) {
            analytics.getReports().addAll(userAnalytics.getReports());
            analytics.setUpdatedAt(String.valueOf(LocalDateTime.now()));
            return repository.save(analytics);
        } else {
            userAnalytics.setCreatedAt(String.valueOf(LocalDateTime.now()));
            return repository.save(userAnalytics);
        }
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
        final UserAnalytics analytics = repository.findByUserId(userId);
        if (analytics != null) {
            analytics.getReports()
                    .removeIf(analysisReport -> analysisReport.getId().equals(reportId));

            repository.save(analytics);
        }
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
        final UserAnalytics analytics = repository.findByUserId(userId);
        List<String> result = new ArrayList<>();
        if (analytics != null) {
            analytics.getReports()
                    .forEach(analysisReport -> result.add(analysisReport.getId()));
            analytics.getReports().clear();

            repository.save(analytics);
        }
        return result;
    }
}
