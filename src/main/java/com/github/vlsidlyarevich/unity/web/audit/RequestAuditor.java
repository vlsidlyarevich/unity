package com.github.vlsidlyarevich.unity.web.audit;

import com.github.vlsidlyarevich.unity.web.security.model.UserAuthentication;

import javax.servlet.http.HttpServletRequest;

public interface RequestAuditor {

    void logRequest(UserAuthentication authentication, HttpServletRequest request,
                    Object handler);
}
