package com.github.vlsidlyarevich.unity.db.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Slf4j
@Aspect
@Component
public class DatabaseServicesLoggingAspect {

    @Before("execution(* com.github.vlsidlyarevich.unity.db.service"
            + ".impl.SimpleUserService.*(..)))")
    public void userServiceLog(final JoinPoint joinPoint) {
        log.info("User service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.db.service"
            + ".impl.SimpleUserSocialService.*(..)))")
    public void userSocialServiceLog(final JoinPoint joinPoint) {
        log.info("UserSocial service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.db.service"
            + ".impl.SimpleUserAnalyticsService.*(..)))")
    public void userAnalyticsServiceLog(final JoinPoint joinPoint) {
        log.info("UserAnalytics service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.db.service"
            + ".impl.FileSystemStorageService.*(..)))")
    public void storageServiceLog(final JoinPoint joinPoint) {
        log.info("Storage service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }
}
