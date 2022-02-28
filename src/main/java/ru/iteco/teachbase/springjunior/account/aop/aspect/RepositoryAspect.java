package ru.iteco.teachbase.springjunior.account.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryAspect {
    private static final Logger LOG = LoggerFactory.getLogger(RepositoryAspect.class);

    @Before("within(ru.iteco.teachbase.springjunior.account.aop.repository.*)")
    public void beforeAllRepositoryAdvice() {
        LOG.info("beforeAllRepositoryAdvice: try call any method in 'repository'");
    }
}
