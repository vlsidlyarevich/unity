package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.model.UserSocial;

import java.util.List;

public interface UserSocialService {

    UserSocial create(UserSocial object);

    UserSocial find(String id);

    UserSocial findByUserId(String userId);

    List<UserSocial> findAll();

    UserSocial update(String id, UserSocial object);

    String delete(String id);

    String deleteByUserId(String userId);
}
