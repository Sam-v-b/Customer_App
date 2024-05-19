package com.sam.restApp.utility;

import com.sam.restApp.RestAppApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LogManager.getLogger(LoggingAspect.class);
    @AfterThrowing(pointcut = "execution(* com.sam.restApp.service.*Impl.*(..))",throwing = "exc")
    public void logServiceException(Exception exc) {
        log.error(exc.getMessage());
    }
}
