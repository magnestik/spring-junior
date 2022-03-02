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
    public Object aroundAllMethodThrow(ProceedingJoinPoint proceedingJoinPoint) {
        String shortStringSignature = proceedingJoinPoint.getSignature().toShortString();
        LOG.info("aroundAllMethodThrow: START METHOD {}", shortStringSignature);

        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            LOG.error("Exception proceed! Return NULL", e);
            return null;
        } finally {
            LOG.info("aroundAllMethodThrow: END METHOD: {}", shortStringSignature);
        }
    }

    @Pointcut("within(ru.iteco.teachbase.springjunior.account.aop..*)")
    public void allMethodInRepo() {}
}
