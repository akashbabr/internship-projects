package com.humancloud.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.humancloud.annotations.LogExecution;

@Aspect
@Component
public class LoggingAspect 
{
    @Around("@annotation(logExecution)")
    public Object logExecution( ProceedingJoinPoint joinPoint,
                                LogExecution logExecution) throws Throwable {

        String operation = logExecution.operation();
        boolean enabled = logExecution.enabled();

        if (!enabled) {
            return joinPoint.proceed();
        }

        long startTime = System.currentTimeMillis();

        System.out.println("[" + operation + "] Method started: "
                + joinPoint.getSignature().getName());

        Object[] args = joinPoint.getArgs();

        System.out.println("Arguments:");

        for (Object arg : args) {
            System.out.println(arg);
        }

        Object result = joinPoint.proceed();

        System.out.println("[" + operation + "] Method completed: "
                + joinPoint.getSignature().getName());

        long executionTime = System.currentTimeMillis() - startTime;

        System.out.println("Execution time: " + executionTime + " ms");

        return result;
    }
}