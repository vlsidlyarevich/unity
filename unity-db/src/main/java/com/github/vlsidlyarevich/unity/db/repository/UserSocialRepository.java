package com.github.vlsidlyarevich.unity.db.repository;

import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSocialRepository extends MongoRepository<UserSocial, String> {

    UserSocial findByUserId(String userId);

    void deleteByUserId(String userId);
}
