package com.github.vlsidlyarevich.unity.web.audit;

import com.github.vlsidlyarevich.unity.auth.models.UserAuthentication;
import com.github.vlsidlyarevich.unity.common.audit.Auditor;
import com.github.vlsidlyarevich.unity.db.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
@Component
public class HttpRequestAuditor implements RequestAuditor {

    private final Auditor auditor;

    @Autowired
    public HttpRequestAuditor(final Auditor auditor) {
        this.auditor = auditor;
    }

    @Override
    public void logRequest(final UserAuthentication authentication,
                           final HttpServletRequest request, final Object handler) {
        if (Objects.isNull(authentication)) {
            logUnauthenticatedRequest(request, handler);
        } else {
            logAuthenticatedRequest(authentication, request, handler);
        }
    }

    private void logUnauthenticatedRequest(final HttpServletRequest request,
                                           final Object handler) {
        Method requestedMethod = ((HandlerMethod) handler).getMethod();

        auditor.logController("Unauthorized", requestedMethod.getName(), request.getRequestURI());
    }

    private void logAuthenticatedRequest(final UserAuthentication authentication,
                                         final HttpServletRequest request, final Object handler) {
        Method requestedMethod = ((HandlerMethod) handler).getMethod();
        User user = (User) authentication.getDetails();

        auditor.logController(user.getUsername(), requestedMethod.getName(), request.getRequestURI());
    }
}
