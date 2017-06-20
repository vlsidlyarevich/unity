package com.github.vlsidlyarevich.unity.git.audit.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class GitLoggingAspect {

    @Pointcut("execution(* com.github.vlsidlyarevich.unity.git.service.*.*(..)))")
    public void service() {

    }
}
