package com.github.vlsidlyarevich.unity.twitter.audit.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TwitterLoggingPointcut {

    @Pointcut("execution(* com.github.vlsidlyarevich.unity.twitter.service.*.*(..)))")
    public void service() {

    }
}
