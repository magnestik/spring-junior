package ru.iteco.teachbase.springjunior.account.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import ru.iteco.teachbase.springjunior.account.homework.service.ExternalService;
import ru.iteco.teachbase.springjunior.account.homework.service.Flow;

@ComponentScan
@EnableAspectJAutoProxy
@PropertySource("classpath:home.properties")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        ExternalService externalService = applicationContext.getBean(ExternalService.class);
        externalService.getExternalInfo(1);

        Flow flow = applicationContext.getBean(Flow.class);
        flow.run(3);
//        flow.run(2);
//        flow.run(3);
//        flow.run(4);

        applicationContext.close();
    }
}
