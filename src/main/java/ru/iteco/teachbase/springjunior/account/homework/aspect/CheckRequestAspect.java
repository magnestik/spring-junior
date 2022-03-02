package ru.iteco.teachbase.springjunior.account.homework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.teachbase.springjunior.account.homework.model.ExternalInfo;

@Aspect
@Component
public class CheckRequestAspect {
    private static final Logger LOG = LoggerFactory.getLogger(CheckRequestAspect.class);

    @Value("${id-not-process}")
    private Integer idNotProcess;

    @Around(value = "externalInfoArgMethod(externalInfo) && checkRequestAnnotatedMethod()", argNames = "proceedingJoinPoint,externalInfo")
    public Object argWithExternalInfoMethodAdvice(ProceedingJoinPoint proceedingJoinPoint, ExternalInfo externalInfo) throws Throwable {
        String shortSignature = proceedingJoinPoint.getSignature().toShortString();
        LOG.info("argWithExternalInfoMethodAdvice: Method {} with arg externalInfo {}", shortSignature, externalInfo);
        if (!idNotProcess.equals((externalInfo).getId())) {
            LOG.info("argWithExternalInfoMethodAdvice: Start ExternalInfoProcess.run()");
            return proceedingJoinPoint.proceed();
        } else {
            LOG.info("argWithExternalInfoMethodAdvice: ExternalInfo.getId == config.id-not-process ({})", idNotProcess);
            throw new RuntimeException("externalInfo.id = " + idNotProcess);
        }
    }

    @Pointcut(value = "execution(* *(..,ru.iteco.teachbase.springjunior.account.homework.model.ExternalInfo,..)) && args(externalInfo,..)")
    public void externalInfoArgMethod(ExternalInfo externalInfo) {
    }

    @Pointcut("@annotation(ru.iteco.teachbase.springjunior.account.homework.annotation.CheckRequest)")
    public void checkRequestAnnotatedMethod() {

    }
}
