package ru.iteco.teachbase.springjunior.account.ioc;

import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Objects;

@Component
public class EncryptResultBeanPostProcessor implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(EncryptResultBeanPostProcessor.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcessAfterInitialization: beanName: {}, beanClass: {}", beanName, bean.getClass().getSimpleName());
        if (bean instanceof IntValue) {
            log.info("Bean is IntValue class");
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.addAdvice((MethodInterceptor) invocation -> {
                log.info("Before call method in IntValue");
                Object proceed = invocation.proceed();
                if (invocation.getMethod().isAnnotationPresent(EncryptResult.class)) {
                    log.info("Call method, result of which need to be encrypted");
                    return encrypt(proceed);
                }
                return proceed;
            });
            return proxyFactory.getProxy();
        }
        return bean;
    }

    private Object encrypt(Object proceed) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        cipher.init(Cipher.ENCRYPT_MODE, keyGenerator.generateKey());
        byte[] bytes = cipher.doFinal(Objects.requireNonNull(SerializationUtils.serialize(proceed)));
        return Base64.getEncoder().encodeToString(bytes);
    }
}
