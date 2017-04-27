package com.github.vlsidlyarevich.unity.web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    @Before("execution(* com.github.vlsidlyarevich.unity.db.service.impl.SimpleUserService.*(..)))")
    public void userServiceLog(JoinPoint joinPoint) {
        logger.info("User service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.db.service.impl.SimpleUserSocialService.*(..)))")
    public void userSocialServiceLog(JoinPoint joinPoint) {
        logger.info("UserSocial service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.db.service.impl.SimpleUserAnalyticsService.*(..)))")
    public void userAnalyticsServiceLog(JoinPoint joinPoint) {
        logger.info("UserAnalytics service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }
}
