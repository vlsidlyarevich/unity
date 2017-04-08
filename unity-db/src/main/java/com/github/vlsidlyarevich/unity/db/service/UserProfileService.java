package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.model.UserSocial;

import java.util.List;

public interface UserProfileService {

    UserSocial create(UserSocial object);

    UserSocial find(String id);

    UserSocial findByUsername(String userName);

    List<UserSocial> findAll();

    UserSocial update(String id, UserSocial object);

    String delete(String id);
}
