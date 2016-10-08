package com.github.vlsidlyarevich.unity.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * Created by vlad on 18.09.16.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.github.vlsidlyarevich.unity.service.impl.WorkerProfileServiceImpl.*(..)))")
    public void workerServiceLog(JoinPoint joinPoint) {
        logger.info("Worker profile service : " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.github.vlsidlyarevich.unity.service.impl.WorkerProfileSearchServiceImpl.*(..)))")
    public void workerSearchServiceLog(JoinPoint joinPoint) {
        logger.info("Worker profile service : " + joinPoint.getSignature().getName() + "\n With arguments : "
                + Arrays.toString(joinPoint.getArgs()));
    }
}
