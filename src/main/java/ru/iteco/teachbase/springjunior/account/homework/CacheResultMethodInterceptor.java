package ru.iteco.teachbase.springjunior.account.homework;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CacheResultMethodInterceptor implements MethodInterceptor {
    private static final Logger log = LoggerFactory.getLogger(CacheResultMethodInterceptor.class);
    private static final Map<String, Map<MethodArgs, Object>> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Before call method in CacheResult");
        Object result = null;
        Method method = invocation.getMethod();
        boolean isAnnotationMethod = method.isAnnotationPresent(CacheResult.class);
        if (!isAnnotationMethod) {
            for (Method declaredMethod : Objects.requireNonNull(invocation.getThis()).getClass().getDeclaredMethods()) {
                if (method.getName().equals(declaredMethod.getName()) &&
                    AnnotationUtils.findAnnotation(declaredMethod, CacheResult.class) != null) {
                    isAnnotationMethod = true;
                    break;
                }
            }
        }
        if (isAnnotationMethod) {
            log.info("Call method, result of which need to be added to Cache");
            String methodName = method.getName();
            Map<MethodArgs, Object> methodArgsObjectMap = cacheMap.get(methodName);
            Object[] methodArguments = invocation.getArguments();
            final MethodArgs methodArgs = getMethodArgs(methodArguments);
            log.info("Check cache result by method with args: {}({})", methodName, methodArguments);
            if (methodArgsObjectMap == null) {
                result = invocation.proceed();
                methodArgsObjectMap = new ConcurrentHashMap<>();
                methodArgsObjectMap.put(methodArgs, result);
                cacheMap.put(methodName, methodArgsObjectMap);
                log.info("Method result added to cache");
            } else {
                result = methodArgsObjectMap.get(methodArgs);
                if (result == null) {
                    log.info("Call original method and record result into cache");
                    result = invocation.proceed();
                    methodArgsObjectMap.put(methodArgs, result);
                } else {
                    log.info("Return result from cache: method: {}({}), result: {}", methodName, methodArguments, result);
                }
            }
        }
        log.info("cacheMap: {}", cacheMap);
        return result;
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
