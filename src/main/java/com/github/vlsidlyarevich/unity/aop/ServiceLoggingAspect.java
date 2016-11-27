package com.github.vlsidlyarevich.unity.aop;

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

    @Before("execution(* com.github.vlsidlyarevich.unity.service.impl.WorkerProfileServiceImpl.*(..)))")
    public void workerServiceLog(JoinPoint joinPoint) {
        logger.info("Worker profile service : " + joinPoint.getSignature().getName()
                + "\n With parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.service.impl.WorkerProfileSearchServiceImpl.*(..)))")
    public void workerSearchServiceLog(JoinPoint joinPoint) {
        logger.info("Worker profile search service : " + joinPoint.getSignature().getName()
                + "\n With arguments : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.service.impl.FileSystemStorageService.*(..)))")
    public void imageServiceLog(JoinPoint joinPoint) {
        logger.info("Image service : " + joinPoint.getSignature().getName()
                + "\n With arguments : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.service.impl.VacancyServiceImpl.*(..)))")
    public void vacancyServiceLog(JoinPoint joinPoint) {
        logger.info("Vacancy service : " + joinPoint.getSignature().getName()
                + "\n With arguments : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.service.impl.CandidateServiceImpl.*(..)))")
    public void candidateServiceLog(JoinPoint joinPoint) {
        logger.info("Candidate service : " + joinPoint.getSignature().getName()
                + "\n With arguments : " + Arrays.toString(joinPoint.getArgs()));
    }
}
