package com.github.vlsidlyarevich.unity.db.audit.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DatabaseLoggingPointcut {

    @Pointcut("execution(* com.github.vlsidlyarevich.unity.db.service.*.*(..)))")
    public void service() {

    }
}
