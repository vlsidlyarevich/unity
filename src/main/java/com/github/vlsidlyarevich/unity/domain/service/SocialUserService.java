package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.model.User;
import org.springframework.social.connect.Connection;

public interface SocialUserService {

    User create(Connection<?> connection);

    void delete(String username);
}
