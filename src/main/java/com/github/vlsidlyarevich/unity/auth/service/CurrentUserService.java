package com.github.vlsidlyarevich.unity.auth.service;

public interface CurrentUserService {

    boolean canAccessUser(String userId);
}
