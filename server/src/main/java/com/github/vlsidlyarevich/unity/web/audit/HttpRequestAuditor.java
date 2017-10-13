package com.github.vlsidlyarevich.unity.web.audit;

import com.github.vlsidlyarevich.unity.common.audit.Auditing;
import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.web.security.model.UserAuthentication;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HttpRequestAuditor implements RequestAuditor {

    private final Auditing auditing;

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
        try {
            final Method requestedMethod = ((HandlerMethod) handler).getMethod();

            auditing.logController("Unauthorized", requestedMethod.getName(),
                    request.getRequestURI());
        } catch (ClassCastException ignored) {

        }
    }

    private void logAuthenticatedRequest(final UserAuthentication authentication,
                                         final HttpServletRequest request, final Object handler) {
        final Method requestedMethod = ((HandlerMethod) handler).getMethod();
        final User user = (User) authentication.getDetails();

        auditing.logController(user.getUsername(), requestedMethod.getName(),
                request.getRequestURI());
    }
}
