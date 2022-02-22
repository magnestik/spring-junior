package ru.iteco.teachbase.springjunior.account.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Flow {
    private static final Logger log = LoggerFactory.getLogger(Flow.class);

    private final Process process;
    private final ExternalService externalService;

    public Flow(ExternalService externalService, @Lazy Process process) {
        this.process = process;
        this.externalService = externalService;
    }

    public void run(Integer id) {
        ExternalInfo externalInfo = externalService.getExternalInfo(id);
        if (externalInfo.getInfo() == null) {
            log.info(process.getClass().getSimpleName());
        } else {
            process.run(externalInfo);
        }
    }
}
