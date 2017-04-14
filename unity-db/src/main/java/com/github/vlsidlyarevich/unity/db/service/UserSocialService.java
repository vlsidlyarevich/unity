package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.model.UserSocial;

import java.util.List;

public interface UserSocialService {

    UserSocial create(UserSocial object);

    UserSocial find(String id);

    UserSocial findByUserId(String userId);

    List<UserSocial> findAll();

    UserSocial update(UserSocial object);

    String delete(String id);
}
