package com.github.vlsidlyarevich.unity.web.security.social.audit;

import com.github.vlsidlyarevich.unity.common.audit.Auditing;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SocialServicesLoggingAspect {

    private final Auditing auditing;

    @Before("com.github.vlsidlyarevich.unity.web"
            + ".security.social.audit.pointcut.SocialLoggingPointcut.adapter()")
    public void logAdapter(final JoinPoint joinPoint) {
        auditing.logAdapter(joinPoint.getTarget().toString());
    }
}
