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
public class ControllerLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.AuthenticationController.*(..)))")
    public void authControllerLog(JoinPoint joinPoint) {
        logger.info("Auth controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.SignUpController.*(..)))")
    public void signUpControllerLog(JoinPoint joinPoint) {
        logger.info("Sign up controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.UsersController.*(..)))")
    public void usersControllerLog(JoinPoint joinPoint) {
        logger.info("Users controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.UserController.*(..)))")
    public void userControllerLog(JoinPoint joinPoint) {
        logger.info("User controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.UserSocialController.*(..)))")
    public void userSocialControllerLog(JoinPoint joinPoint) {
        logger.info("UserSocial controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.UserAnalyticsController.*(..)))")
    public void userAnalyticsControllerLog(JoinPoint joinPoint) {
        logger.info("UserAnalytics controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.GitProfileController.*(..)))")
    public void gitProfileControllerLog(JoinPoint joinPoint) {
        logger.info("Git profile controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }
}
