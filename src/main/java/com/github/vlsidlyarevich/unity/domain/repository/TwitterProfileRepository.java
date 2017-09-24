package com.github.vlsidlyarevich.unity.domain.repository;

import com.github.vlsidlyarevich.unity.domain.model.TwitterProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterProfileRepository extends MongoRepository<TwitterProfile, String> {

}
