package com.github.vlsidlyarevich.unity.web.audit;


import com.github.vlsidlyarevich.unity.auth.models.UserAuthentication;

import javax.servlet.http.HttpServletRequest;

public interface Audit {

    void logRequest(UserAuthentication authentication, HttpServletRequest request,
                    Object handler);
}
