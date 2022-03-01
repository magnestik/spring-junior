package ru.iteco.teachbase.springjunior.account.homework;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.iteco.teachbase.springjunior.account.homework.annotation.CacheResult;
import ru.iteco.teachbase.springjunior.account.homework.annotation.PrintResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class ProxyMethodBeanPostProcessor implements BeanPostProcessor {
    private final Map<Class<? extends Annotation>, MethodInterceptor> methodInterceptorMap = Map.of(
        PrintResult.class, new PrintResultMethodInterceptor(),
        CacheResult.class, new CacheResultMethodInterceptor()
    );

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] declaredMethods = bean.getClass().getDeclaredMethods();
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        for (Method declaredMethod : declaredMethods) {
            for (Map.Entry<Class<? extends Annotation>, MethodInterceptor> proxyEntry : methodInterceptorMap.entrySet()) {
                if (declaredMethod.isAnnotationPresent(proxyEntry.getKey())) {
                    proxyFactory.addAdvice(proxyEntry.getValue());
                }
            }
        }
        return proxyFactory.getAdvisorCount() > 0 ? proxyFactory.getProxy() : bean;
    }
}
