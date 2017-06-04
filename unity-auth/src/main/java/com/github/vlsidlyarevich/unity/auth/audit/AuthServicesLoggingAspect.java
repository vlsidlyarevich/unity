package com.github.vlsidlyarevich.unity.auth.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class AuthServicesLoggingAspect {

    @Before("execution(* com.github.vlsidlyarevich.unity.auth.service"
            + ".JsonWebTokenService.*(..)))")
    public void tokenServiceLog(final JoinPoint joinPoint) {
        log.info("Token service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.auth.service"
            + ".JsonWebTokenAuthenticationService.*(..)))")
    public void tokenAuthenticationServiceLog(final JoinPoint joinPoint) {
        log.info("Token Authentication service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.auth.service"
            + ".SecurityContextCurrentUserService.*(..)))")
    public void currentUserServiceLog(final JoinPoint joinPoint) {
        log.info("Current User service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.auth.service"
            + ".DatabaseUserDetailsService.*(..)))")
    public void databaseUserDetailsServiceLog(final JoinPoint joinPoint) {
        log.info("Database User details service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }
}
