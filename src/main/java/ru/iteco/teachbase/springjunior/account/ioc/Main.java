package ru.iteco.teachbase.springjunior.account.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "ru.iteco.teachbase.springjunior.account")
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(Main.class);
        AccountService bean = app.getBean(AccountService.class);
        PersonalInformationService personalInformationService = app.getBean(PersonalInformationService.class);
        System.out.println(personalInformationService.getPersonalInfo(5).getId());
    }
}
