package com.github.vlsidlyarevich.unity.web.security.audit.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AuthLoggingPointcut {

    @Pointcut("execution(* com.github.vlsidlyarevich.unity.web.security.service.*.*(..)))")
    public void service() {

    }
}
