package ru.iteco.teachbase.springjunior.account.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "ru.iteco.teachbase.springjunior.account")
@PropertySource("classpath:application.properties")
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(Main.class);
        AccountService accountService = app.getBean(AccountService.class);
        log.info("AccountService: {}", accountService);

        PersonalInformationService personalInformationService = app.getBean(PersonalInformationService.class);
        log.info("ID: {}", personalInformationService.getPersonalInfo(5).getId());
        String intValueInfo = personalInformationService.getIntValueInfo();
        log.info(intValueInfo);
        log.info(personalInformationService.getProxyLazyInfo());
        log.info("personalInformationService.getLazyInfo(): ", personalInformationService.getLazyInfo());

/*
        PersonalInformationService personalInformationService1 = app.getBean(PersonalInformationService.class);
        String intValueInfo1 = personalInformationService1.getIntValueInfo();
        log.info(intValueInfo1);
*/

        IntValue intValue1 = app.getBean(IntValue.class);
        IntValue intValue2 = app.getBean(IntValue.class);
        log.info("IntValue1 toString: {}", intValue1);
        log.info("IntValue2 toString: {}", intValue2);
        log.info("IntValue1 getInfo: {}", intValue1.getInfo());
        log.info("IntValue2 getInfo: {}", intValue2.getInfo());
    }
}
