package com.github.vlsidlyarevich.unity.web.interceptor;

import com.github.vlsidlyarevich.unity.web.audit.HttpRequestAuditor;
import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestLoggingInterceptor extends HandlerInterceptorAdapter {

    private final AuthenticationFacade authenticationFacade;
    private final HttpRequestAuditor audit;

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        audit.logRequest(authenticationFacade.getAuthentication(),
                request, handler);
        return super.preHandle(request, response, handler);
    }
}
