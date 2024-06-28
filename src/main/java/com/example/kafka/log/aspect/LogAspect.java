package com.example.kafka.log.aspect;

import com.example.kafka.log.service.FileLogService;
import com.example.kafka.log.service.KafkaLoggingService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final FileLogService fileLogService;
    private final KafkaLoggingService kafkaLoggingService;

    public LogAspect(FileLogService fileLogService, KafkaLoggingService kafkaLoggingService) {
        this.fileLogService = fileLogService;
        this.kafkaLoggingService = kafkaLoggingService;
    }

    @Around("@annotation(com.example.kafka.log.aspect.FileLoggable)")
    public Object logUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        // Log user action before method invocation
        String name = joinPoint.getSignature().getName();
        Object[] joinPointArgs = joinPoint.getArgs();
        fileLogService.logUserAction(name + " " + Arrays.toString(joinPointArgs));

        // Proceed with the method execution
        Object result = joinPoint.proceed();

        // Log user action after method execution
        fileLogService.logUserAction(result.toString());
        fileLogService.close();

        return result;
    }

    @Around("@annotation(com.example.kafka.log.aspect.KafkaLoggable)")
    public Object kafkaLogUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        // Log user action before method invocation
        Object[] joinPointArgs = joinPoint.getArgs();
        String user = "Rahim";
        kafkaLoggingService.logUserAction(user, Arrays.toString(joinPointArgs));

        // Proceed with the method execution
        Object result = joinPoint.proceed();

        // Log user action after method execution
        kafkaLoggingService.logUserAction(user, result.toString());

        return result;
    }
}
