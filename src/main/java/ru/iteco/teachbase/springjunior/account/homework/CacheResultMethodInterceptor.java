package ru.iteco.teachbase.springjunior.account.homework;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheResultMethodInterceptor implements MethodInterceptor {
    private static final Logger log = LoggerFactory.getLogger(CacheResultMethodInterceptor.class);
    private static final Map<String, Object> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Before call method in CacheResult");
        Object proceed = null;
        Method method = invocation.getMethod();
        if (method.isAnnotationPresent(CacheResult.class)) {
            log.info("Call method, result of which need to be added to Cache");
            String methodName = method.getName();
            if (cacheMap.get(methodName) == null) {
                proceed = invocation.proceed();
                cacheMap.put(methodName, proceed);
                log.info("Method result added to cache");
            } else {
                log.info("Method result found in cache");
                proceed = cacheMap.get(methodName);
            }
        }
        log.info("cacheMap: {}", cacheMap);
        return proceed;
    }
}
