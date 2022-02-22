package ru.iteco.teachbase.springjunior.account.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class PrintBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(PrintBeanFactoryPostProcessor.class);
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            log.info("beanDefinitionName: {}", beanDefinitionName);
        }
    }
}
