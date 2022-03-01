package ru.iteco.teachbase.springjunior.account.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ThrowCheckAspect {
    private static final Logger LOG = LoggerFactory.getLogger(ThrowCheckAspect.class);

    @Around("allMethodInRepo()")
    public Object aroundAllMethodThrow(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOG.info("aroundAllMethodThrow: START METHOD {}", proceedingJoinPoint.getSignature().toShortString());
        try {
            return proceedingJoinPoint.proceed();
        } catch (Exception e) {
            LOG.error("Exception proceed! Return NULL", e);
            return null;
        } finally {
            LOG.info("aroundAllMethodThrow: END METHOD: {}", proceedingJoinPoint.getSignature().toShortString());
        }
    }

    @Pointcut("within(ru.iteco.teachbase.springjunior.account.aop..*)")
    public void allMethodInRepo() {}
}
