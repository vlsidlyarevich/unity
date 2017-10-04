package com.github.vlsidlyarevich.unity.web.audit;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface RequestAuditor {

    void logRequest(Authentication authentication, HttpServletRequest request,
                    Object handler);
}
