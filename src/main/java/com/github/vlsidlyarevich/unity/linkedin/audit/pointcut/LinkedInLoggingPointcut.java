package com.github.vlsidlyarevich.unity.linkedin.audit.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LinkedInLoggingPointcut {

    @Pointcut("execution(* com.github.vlsidlyarevich.unity.linkedin.service.*.*(..)))")
    public void service() {

    }
}
