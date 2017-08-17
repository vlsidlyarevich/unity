package com.github.vlsidlyarevich.unity.web.security.social.service;

import com.github.vlsidlyarevich.unity.db.domain.User;
import org.springframework.social.connect.Connection;

public interface SocialUserService {

    User create(Connection<?> connection, String language);

    String delete(String username);
}
