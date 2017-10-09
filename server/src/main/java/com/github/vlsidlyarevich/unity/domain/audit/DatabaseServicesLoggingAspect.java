package com.github.vlsidlyarevich.unity.domain.audit;

import com.github.vlsidlyarevich.unity.common.audit.Auditing;
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
public class DatabaseServicesLoggingAspect {

    private final Auditing auditing;

    @Autowired
    public DatabaseServicesLoggingAspect(final Auditing auditing) {
        this.auditing = auditing;
    }

    @Before("com.github.vlsidlyarevich.unity.domain.audit.pointcut.DatabaseLoggingPointcut.service()")
    public void logService(final JoinPoint joinPoint) {
        auditing.logService(joinPoint.getTarget().toString(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "com.github.vlsidlyarevich.unity.domain.audit.pointcut"
            + ".DatabaseLoggingPointcut.service()", throwing = "exception")
    public void logException(final JoinPoint joinPoint, final Throwable exception) {
        auditing.logException(joinPoint.getTarget().toString(), exception);
    }
}
