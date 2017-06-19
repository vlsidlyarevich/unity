package com.github.vlsidlyarevich.unity.web.security.service;

public interface CurrentUserService {

    boolean canAccessUser(String userId);
}
