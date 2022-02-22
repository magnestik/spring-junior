package ru.iteco.teachbase.springjunior.account.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class InjectRandomBeanPostProcessor implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(InjectRandomBeanPostProcessor.class);
    private final Random rand = SecureRandom.getInstanceStrong();

    public InjectRandomBeanPostProcessor() throws NoSuchAlgorithmException {
        // SecureRandom getInstanceStrong() throws NoSuchAlgorithmException
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcessBeforeInitialization: beanName: {}, beanClass: {}", beanName, bean.getClass().getSimpleName());
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(InjectRandom.class)) {
                declaredField.setAccessible(true);
                InjectRandom injectRandom = declaredField.getAnnotation(InjectRandom.class);
                Integer randomInt = getRandomInt(injectRandom.min(), injectRandom.max());
                log.info("Set random value in {}, value {}", declaredField.getName(), randomInt);
                ReflectionUtils.setField(declaredField, bean, randomInt);
                declaredField.setAccessible(false);
            }
        }
        return bean;
    }

    private Integer getRandomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return rand.nextInt((max - min) + 1) + min;
    }
}
