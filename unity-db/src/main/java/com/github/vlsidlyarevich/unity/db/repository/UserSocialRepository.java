package com.github.vlsidlyarevich.unity.db.repository;

import com.github.vlsidlyarevich.unity.db.model.UserSocial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSocialRepository extends MongoRepository<UserSocial, String> {

    UserSocial findByUserId(String userName);

    void deleteByUserId(String userId);
}
