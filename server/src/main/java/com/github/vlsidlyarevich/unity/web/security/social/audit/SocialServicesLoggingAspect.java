package com.github.vlsidlyarevich.unity.web.security.social.audit;

import com.github.vlsidlyarevich.unity.common.audit.Auditing;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SocialServicesLoggingAspect {

    private final Auditing auditing;

    @Before("com.github.vlsidlyarevich.unity.web"
            + ".security.social.audit.pointcut.SocialLoggingPointcut.service()")
    public void logService(final JoinPoint joinPoint) {
        auditing.logService(joinPoint.getTarget().toString(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @Before("com.github.vlsidlyarevich.unity.web"
            + ".security.social.audit.pointcut.SocialLoggingPointcut.adapter()")
    public void logAdapter(final JoinPoint joinPoint) {
        auditing.logAdapter(joinPoint.getTarget().toString());
    }

    @AfterThrowing(pointcut = "com.github.vlsidlyarevich.unity.web"
            + ".security.social.audit.pointcut.SocialLoggingPointcut.service()", throwing = "exception")
    public void logServiceException(final JoinPoint joinPoint, final Throwable exception) {
        auditing.logException(joinPoint.getTarget().toString(), exception);
    }
}
