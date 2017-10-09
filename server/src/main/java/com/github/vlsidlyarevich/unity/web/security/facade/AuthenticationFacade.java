package com.github.vlsidlyarevich.unity.web.security.facade;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    Authentication getAuthentication();
}
