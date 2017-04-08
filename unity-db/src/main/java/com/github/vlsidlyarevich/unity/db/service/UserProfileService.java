package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.model.UserProfile;

import java.util.List;

public interface UserProfileService {

    UserProfile create(UserProfile object);

    UserProfile find(String id);

    UserProfile findByUsername(String userName);

    List<UserProfile> findAll();

    UserProfile update(String id, UserProfile object);

    String delete(String id);
}
