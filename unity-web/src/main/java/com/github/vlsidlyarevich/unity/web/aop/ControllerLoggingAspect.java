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

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.WorkersProfileController.*(..)))")
    public void workerProfileControllerLog(JoinPoint joinPoint) {
        logger.info("Worker profile controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.WorkerProfileDeleteQueryController.*(..)))")
    public void workerProfileDeleteQueryControllerLog(JoinPoint joinPoint) {
        logger.info("Worker profile delete query controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.WorkerProfileSearchController.*(..)))")
    public void workerProfileSearchControllerLog(JoinPoint joinPoint) {
        logger.info("Worker profile search controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.ImageController.*(..)))")
    public void imageControllerLog(JoinPoint joinPoint) {
        logger.info("Image controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.VacancyController.*(..)))")
    public void vacancyControllerLog(JoinPoint joinPoint) {
        logger.info("Vacancy controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.VacancyDeleteQueryController.*(..)))")
    public void vacancyDeleteQueryControllerLog(JoinPoint joinPoint) {
        logger.info("Vacancy delete query controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.CandidateController.*(..)))")
    public void candidateControllerLog(JoinPoint joinPoint) {
        logger.info("Candidate controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.web.controller.CandidateDeleteQueryController.*(..)))")
    public void candidateDeleteQueryControllerLog(JoinPoint joinPoint) {
        logger.info("Candidate delete query controller : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }
}
