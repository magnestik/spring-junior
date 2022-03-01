package ru.iteco.teachbase.springjunior.account.homework;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Objects;

public class PrintResultMethodInterceptor implements MethodInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(PrintResultMethodInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LOG.info("Before call method in PrintResult");
        Method method = invocation.getMethod();
        boolean isAnnotationMethod = method.isAnnotationPresent(PrintResult.class);
        if (!isAnnotationMethod) {
            for (Method declaredMethod : Objects.requireNonNull(invocation.getThis()).getClass().getDeclaredMethods()) {
                if (method.getName().equals(declaredMethod.getName()) &&
                    AnnotationUtils.findAnnotation(declaredMethod, PrintResult.class) != null) {
                    isAnnotationMethod = true;
                    break;
                }
            }
        }
        Object result = invocation.proceed();
        if (isAnnotationMethod) {
            LOG.info("PrintResult: {}", result);
        }

        return result;
    }
}
