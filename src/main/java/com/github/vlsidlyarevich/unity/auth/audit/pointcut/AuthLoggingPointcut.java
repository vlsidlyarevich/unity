package com.github.vlsidlyarevich.unity.auth.audit.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AuthLoggingPointcut {

    @Pointcut("execution(* com.github.vlsidlyarevich.unity.auth.service.*.*(..)))")
    public void service() {

    }
}
