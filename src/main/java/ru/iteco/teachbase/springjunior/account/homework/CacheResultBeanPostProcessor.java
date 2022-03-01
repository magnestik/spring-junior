package ru.iteco.teachbase.springjunior.account.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CacheResultBeanPostProcessor implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(CacheResultBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
/*
        log.info("postProcessAfterInitialization: beanName: {}", beanName);
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CacheResult.class)) {
                log.info("Bean {} is proxy {}. Has annotation @CacheResult on method: {}", beanName, bean.getClass().getSimpleName(), method.getName());
                ProxyFactory proxyFactory = new ProxyFactory(bean);
                proxyFactory.addAdvice(new CacheResultMethodInterceptor());
                return proxyFactory.getProxy();
            }
        }
*/
        return bean;
    }
}
