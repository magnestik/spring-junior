package ru.iteco.teachbase.springjunior.account.homework.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    public static final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);

    @Around("within(ru.iteco.teachbase.springjunior.account.homework.service..*)")
    public Object logAllServiceMethodAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String shortSignature = proceedingJoinPoint.getSignature().toShortString();
        LOG.info("logAllServiceMethodAdvice: Start method: {}", shortSignature);
        Object proceed = proceedingJoinPoint.proceed();
        LOG.info("logAllServiceMethodAdvice: End method: {} with result: {}", shortSignature, proceed);
        return proceed;
    }

    @AfterThrowing(value = "execution(* *(..))", throwing = "exception")
    public void logAllThrownMethodAdvice(JoinPoint joinPoint, Exception exception) {
        String shortSignature = joinPoint.getSignature().toShortString();
        String exceptionString = exception.toString();
        LOG.info("logAllThrownMethodAdvice: Method {} return Exception: {}", shortSignature, exceptionString);
    }

}
