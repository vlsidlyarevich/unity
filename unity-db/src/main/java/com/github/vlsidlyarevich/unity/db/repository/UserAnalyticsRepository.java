package com.github.vlsidlyarevich.unity.db.repository;

import com.github.vlsidlyarevich.unity.db.model.UserAnalytics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnalyticsRepository extends MongoRepository<UserAnalytics, String> {

    UserAnalytics findByUserId(String userId);
}
