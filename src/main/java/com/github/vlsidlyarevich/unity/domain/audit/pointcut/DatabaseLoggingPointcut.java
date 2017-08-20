package com.github.vlsidlyarevich.unity.domain.audit.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DatabaseLoggingPointcut {

    @Pointcut("execution(* com.github.vlsidlyarevich.unity.domain.service.*.*(..)))")
    public void service() {

    }
}
