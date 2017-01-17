package com.github.vlsidlyarevich.unity.db.repository;

import com.github.vlsidlyarevich.unity.db.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
