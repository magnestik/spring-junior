package ru.iteco.teachbase.springjunior.account.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterAspect {
    private static final Logger LOG = LoggerFactory.getLogger(AfterAspect.class);

    @AfterReturning(value = "allGetMethod()", returning = "result")
    public void afterAllGetMethodAdvice(JoinPoint joinPoint, Object result) {
        LOG.info("afterAllGetMethodAdvice: After {} with result: {}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(value = "allSaveMethod()", throwing = "exception")
    public void afterSaveMethodThrowAdvice(JoinPoint joinPoint, Exception exception) {
        LOG.info("afterSaveMethodThrowAdvice: Method {} return Exception: {}",
            joinPoint.getSignature().toShortString(),
            exception.toString());
    }

    @After("allSaveMethod() || allGetMethod()")
    public void afterSaveAndGetMethodAdvice(JoinPoint joinPoint) {
        LOG.info("afterSaveAndGetMethodAdvice: Method {} with args: {}",
            joinPoint.getSignature().toShortString(),
            joinPoint.getArgs());
    }

    @Pointcut("execution(* save*(..))")
    public void allSaveMethod() {}

    @Pointcut("execution(* get*(..))")
    public void allGetMethod() {

    }
}
