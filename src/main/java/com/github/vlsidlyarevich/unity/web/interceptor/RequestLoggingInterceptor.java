package com.github.vlsidlyarevich.unity.web.interceptor;

import com.github.vlsidlyarevich.unity.auth.models.UserAuthentication;
import com.github.vlsidlyarevich.unity.auth.security.AuthenticationFacade;
import com.github.vlsidlyarevich.unity.web.audit.HttpRequestAuditor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class RequestLoggingInterceptor extends HandlerInterceptorAdapter {

    private final AuthenticationFacade authenticationFacade;

    private final HttpRequestAuditor audit;

    @Autowired
    public RequestLoggingInterceptor(final AuthenticationFacade authenticationFacade,
                                     final HttpRequestAuditor audit) {
        this.authenticationFacade = authenticationFacade;
        this.audit = audit;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        audit.logRequest((UserAuthentication) authenticationFacade.getAuthentication(),
                request, handler);
        return super.preHandle(request, response, handler);
    }
}
