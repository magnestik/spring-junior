package ru.iteco.teachbase.springjunior.account.homework.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.teachbase.springjunior.account.homework.model.ExternalInfo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class AnnotationAspect {
    private static final Logger LOG = LoggerFactory.getLogger(AnnotationAspect.class);
    private static final Map<String, Map<MethodArgs, Object>> cacheMap = new ConcurrentHashMap<>();

    @Value("${id-not-process}")
    private Integer idNotProcess;

    @Around(value = "allMethodMarkCheckRequestAndHaveExternalInfoInArg(externalInfo)", argNames = "proceedingJoinPoint,externalInfo")
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

    @AfterReturning(value = "printResultAnnotatedMethod()", returning = "result")
    public void printResultMethodAdvice(JoinPoint joinPoint, Object result) {
        String shortString = joinPoint.toShortString();
        LOG.info("printResultMethodAdvice: Method {} PrintResult: {}", shortString, result);
    }
    
    @Around(value = "cacheResultAnnotatedMethod()")
    public Object cacheResultMethodAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOG.info("Call method, result of which need to be added to Cache");
        String methodName = proceedingJoinPoint.getSignature().getName();
        Map<MethodArgs, Object> methodArgsObjectMap = cacheMap.get(methodName);
        Object[] methodArguments = proceedingJoinPoint.getArgs();
        final MethodArgs methodArgs = getMethodArgs(methodArguments);
        LOG.info("Check cache result by method with args: {}({})", methodName, methodArguments);
        Object result;
        if (methodArgsObjectMap == null) {
            result = proceedingJoinPoint.proceed();
            methodArgsObjectMap = new ConcurrentHashMap<>();
            methodArgsObjectMap.put(methodArgs, result);
            cacheMap.put(methodName, methodArgsObjectMap);
            LOG.info("Method result added to cache");
        } else {
            result = methodArgsObjectMap.get(methodArgs);
            if (result == null) {
                LOG.info("Call original method and record result into cache");
                result = proceedingJoinPoint.proceed();
                methodArgsObjectMap.put(methodArgs, result);
            } else {
                LOG.info("Return result from cache: method: {}({}), result: {}", methodName, methodArguments, result);
            }
        }
        LOG.info("cacheMap: {}", cacheMap);
        return result;
    }

    @Pointcut("@annotation(ru.iteco.teachbase.springjunior.account.homework.annotation.CheckRequest) && args(externalInfo,..)")
    public void allMethodMarkCheckRequestAndHaveExternalInfoInArg(ExternalInfo externalInfo) {

    }

    @Pointcut("@annotation(ru.iteco.teachbase.springjunior.account.homework.annotation.PrintResult)")
    public void printResultAnnotatedMethod() {

    }

    @Pointcut("@annotation(ru.iteco.teachbase.springjunior.account.homework.annotation.CacheResult)")
    public void cacheResultAnnotatedMethod() {

    }

    private MethodArgs getMethodArgs(Object[] args) {
        LinkedList<Object> objects = new LinkedList<>();
        Collections.addAll(objects, args);
        return new MethodArgs(objects);
    }

    private static final class MethodArgs {
        private final LinkedList<Object> args;

        private MethodArgs(LinkedList<Object> args) {
            this.args = args;
        }

        public List<Object> getArgs() {
            return args;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MethodArgs that = (MethodArgs) o;
            return args.equals(that.args);
        }

        @Override
        public int hashCode() {
            return Objects.hash(args);
        }

        @Override
        public String toString() {
            return String.format("MethodArgs{args=%s}", args);
        }
    }
}
