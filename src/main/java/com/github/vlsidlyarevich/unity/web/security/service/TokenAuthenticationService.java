package com.github.vlsidlyarevich.unity.web.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface TokenAuthenticationService {

    Optional<Authentication> authenticate(HttpServletRequest request);
}
