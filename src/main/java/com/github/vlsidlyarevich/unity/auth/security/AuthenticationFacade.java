package com.github.vlsidlyarevich.unity.auth.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    Authentication getAuthentication();
}
