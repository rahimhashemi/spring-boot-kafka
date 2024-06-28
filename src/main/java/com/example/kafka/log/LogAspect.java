package com.example.kafka.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Around("@annotation(Loggable)")
    public Object logUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        // Log user action before method invocation
        String name = joinPoint.getSignature().getName();
        Object[] joinPointArgs = joinPoint.getArgs();
        logService.logUserAction(name + " " + Arrays.toString(joinPointArgs));

        // Proceed with the method execution
        Object result = joinPoint.proceed();

        // Log user action after method execution
        logService.logUserAction(result.toString());

        return result;
    }
}
