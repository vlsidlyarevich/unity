package com.github.vlsidlyarevich.unity.domain.repository;

import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnalyticsRepository extends MongoRepository<UserAnalytics, String> {

    UserAnalytics findByUserId(String userId);
}
