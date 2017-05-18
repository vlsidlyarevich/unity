package com.github.vlsidlyarevich.unity.db.service.impl;

import com.github.vlsidlyarevich.unity.common.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.db.model.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.repository.UserAnalyticsRepository;
import com.github.vlsidlyarevich.unity.db.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleUserAnalyticsService implements UserAnalyticsService {

    private final UserAnalyticsRepository repository;

    private final UserService userService;

    @Autowired
    public SimpleUserAnalyticsService(final UserAnalyticsRepository repository, final UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public UserAnalytics add(final UserAnalytics userAnalytics) {
        if (userService.find(userAnalytics.getUserId()) == null) {
            throw new UserNotFoundException("User with user id: " + userAnalytics.getUserId() + " not found");
        }

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
    public UserAnalytics find(String id) {
        return repository.findOne(id);
    }

    @Override
    public UserAnalytics findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<UserAnalytics> findAll() {
        return repository.findAll();
    }

    @Override
    public String delete(String id) {
        repository.delete(id);
        return id;
    }

    @Override
    public String deleteReport(String userId, String reportId) {
        UserAnalytics analytics = repository.findByUserId(userId);
        if (analytics != null) {
            analytics.getReports()
                    .removeIf(analysisReport -> analysisReport.getId().equals(reportId));
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
    public List<String> deleteAllReports(String userId) {
        UserAnalytics analytics = repository.findByUserId(userId);
        List<String> result = new ArrayList<>();
        if (analytics != null) {
            analytics.getReports()
                    .forEach(analysisReport -> result.add(analysisReport.getId()));
            analytics.getReports().clear();
        }
        return result;
    }
}
