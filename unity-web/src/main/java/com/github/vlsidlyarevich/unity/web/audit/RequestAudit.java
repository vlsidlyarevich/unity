package com.github.vlsidlyarevich.unity.web.audit;

import com.github.vlsidlyarevich.unity.auth.models.UserAuthentication;
import com.github.vlsidlyarevich.unity.db.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
@Component
public class RequestAudit implements Audit {

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
        Class requestedController = ((HandlerMethod) handler).getBean().getClass();
        Method requestedMethod = ((HandlerMethod) handler).getMethod();

        log.info("Unauthenticated user"
                + "\n called method: " + requestedMethod.getName()
                + " of controller: " + requestedController.getName());
    }

    private void logAuthenticatedRequest(final UserAuthentication authentication,
                                         final HttpServletRequest request, final Object handler) {
        Class requestedController = ((HandlerMethod) handler).getBean().getClass();
        Method requestedMethod = ((HandlerMethod) handler).getMethod();
        User user = (User) authentication.getDetails();

        log.info("User with name: " + user.getUsername()
                + " and Roles: " + user.getAuthorities()
                + "\n called method: " + requestedMethod.getName()
                + " of controller: " + requestedController.getName());
    }
}
