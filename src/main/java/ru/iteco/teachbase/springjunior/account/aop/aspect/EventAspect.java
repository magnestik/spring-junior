package ru.iteco.teachbase.springjunior.account.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EventAspect {
    private static final Logger LOG = LoggerFactory.getLogger(EventAspect.class);

    @Before("@annotation(ru.iteco.teachbase.springjunior.account.aop.aspect.AspectEvent)")
    public void beforeAllAnnotationAspectEventAdvice() {
        LOG.info("beforeAllAnnotationAspectEventAdvice: try call method mark @AspectEvent");
    }
}
