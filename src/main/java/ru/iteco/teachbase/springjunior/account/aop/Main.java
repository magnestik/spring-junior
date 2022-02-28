package ru.iteco.teachbase.springjunior.account.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.iteco.teachbase.springjunior.account.aop.repository.ExternalRepository;
import ru.iteco.teachbase.springjunior.account.aop.repository.OtherRepository;

@ComponentScan
@EnableAspectJAutoProxy
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        ExternalService externalServiceImpl = applicationContext.getBean("externalServiceImpl", ExternalService.class);
        LOG.info("Result call externalServiceImpl.getInfo(): {}", externalServiceImpl.getInfo(1L));
        LOG.info("Result call externalServiceImpl.getCustomInfo(): {}", externalServiceImpl.getCustomInfo());

        ExternalService externalServiceOther = applicationContext.getBean("externalServiceOther", ExternalService.class);
        LOG.info("Result call externalServiceOther.getInfo(): {}", externalServiceOther.getInfo(2L));

        ExternalRepository externalRepository = applicationContext.getBean(ExternalRepository.class);
        externalRepository.setInfo("INFO IN REPO");

        OtherRepository otherRepository = applicationContext.getBean(OtherRepository.class);
        otherRepository.getInfo();
    }
}
