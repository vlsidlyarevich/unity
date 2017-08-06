package com.github.vlsidlyarevich.unity.web.audit;

import com.github.vlsidlyarevich.unity.common.audit.Auditor;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.web.security.model.UserAuthentication;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Component
@AllArgsConstructor
public class HttpRequestAuditor implements RequestAuditor {

    private final Auditor auditor;

    @Override
    public void logRequest(final Authentication authentication,
                           final HttpServletRequest request, final Object handler) {
        if (authentication instanceof UserAuthentication) {
            logAuthenticatedRequest((UserAuthentication) authentication, request, handler);
        } else {
            logUnauthenticatedRequest(request, handler);
        }
    }

    private void logUnauthenticatedRequest(final HttpServletRequest request,
                                           final Object handler) {
        Method requestedMethod = ((HandlerMethod) handler).getMethod();

        auditor.logController("Unauthorized", requestedMethod.getName(),
                request.getRequestURI());
    }

    private void logAuthenticatedRequest(final UserAuthentication authentication,
                                         final HttpServletRequest request, final Object handler) {
        Method requestedMethod = ((HandlerMethod) handler).getMethod();
        User user = (User) authentication.getDetails();

        auditor.logController(user.getUsername(), requestedMethod.getName(),
                request.getRequestURI());
    }
}
