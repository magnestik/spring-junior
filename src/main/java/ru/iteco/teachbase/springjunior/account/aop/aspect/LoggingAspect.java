package ru.iteco.teachbase.springjunior.account.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("allGetAdvice() && allSetAdvice()")
    public void beforeAllGetAdvice() {
        LOG.info("beforeGetInfoAdvice: try call method get*()");
    }

    @Pointcut("execution(public String get*(..))")
    public void allGetAdvice() {

    }

    @Pointcut("execution(public * set*(..))")
    public void allSetAdvice() {

    }
}
