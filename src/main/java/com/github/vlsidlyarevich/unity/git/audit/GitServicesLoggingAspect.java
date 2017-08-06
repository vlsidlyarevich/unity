package com.github.vlsidlyarevich.unity.git.audit;

import com.github.vlsidlyarevich.unity.common.audit.Auditor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class GitServicesLoggingAspect {

    private final Auditor auditor;

    @Before("com.github.vlsidlyarevich.unity.git.audit.pointcut.GitLoggingAspect.service()")
    public void logService(final JoinPoint joinPoint) {
        auditor.logService(joinPoint.getTarget().toString(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }
}
