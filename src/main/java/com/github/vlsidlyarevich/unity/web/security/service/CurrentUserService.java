package com.github.vlsidlyarevich.unity.web.security.service;

public interface CurrentUserService {

    boolean canAccessUserOrAdmin(String userId);
}
