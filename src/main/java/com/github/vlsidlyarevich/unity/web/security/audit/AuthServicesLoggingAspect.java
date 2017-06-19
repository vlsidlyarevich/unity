package com.github.vlsidlyarevich.unity.web.security.audit;

import com.github.vlsidlyarevich.unity.common.audit.Auditor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AuthServicesLoggingAspect {

    private final Auditor auditor;

    @Autowired
    public AuthServicesLoggingAspect(final Auditor auditor) {
        this.auditor = auditor;
    }

    @Before("com.github.vlsidlyarevich.unity.web.security.audit.pointcut.AuthLoggingPointcut.service()")
    public void logService(final JoinPoint joinPoint) {
        auditor.logService(joinPoint.getTarget().toString(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }
}
