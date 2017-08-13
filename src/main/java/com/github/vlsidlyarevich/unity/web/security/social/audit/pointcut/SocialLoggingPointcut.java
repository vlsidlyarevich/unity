package com.github.vlsidlyarevich.unity.web.security.social.audit.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class SocialLoggingPointcut {

    @Pointcut("execution(* com.github.vlsidlyarevich.unity.web.security.social.service.*.*(..)))")
    public void service() {

    }

    @Pointcut("execution(* com.github.vlsidlyarevich.unity.web.security.social.adapter.*.*(..)))")
    public void adapter() {

    }
}
