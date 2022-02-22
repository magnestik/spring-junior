package ru.iteco.teachbase.springjunior.account.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CheckWarnBeanFactoryPostProcessor implements org.springframework.beans.factory.config.BeanFactoryPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(CheckWarnBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanDefinitionName);
            boolean isPrototype = beanDefinition.isPrototype();
            if (isPrototype) {
                checkCacheResultAnnotation(beanDefinitionName, beanDefinition);
            }
        }
    }

    private void checkCacheResultAnnotation(String beanDefinitionName, BeanDefinition beanDefinition) {
        Class<?> aClass;
        try {
            aClass = Class.forName(beanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Method method : aClass.getMethods()) {
            if (method.isAnnotationPresent(CacheResult.class)) {
                log.warn("BeanDefinition {} Scope=Prototype and has @CacheResult", beanDefinitionName);
                break;
            }
        }
    }
}
