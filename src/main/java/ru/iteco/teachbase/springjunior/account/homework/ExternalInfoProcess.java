package ru.iteco.teachbase.springjunior.account.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExternalInfoProcess implements Process {
    private static final Logger log = LoggerFactory.getLogger(ExternalInfoProcess.class);

    @Value("${id-not-process}")
    private int idNotProcess;

    @Override
    public boolean run(ExternalInfo externalInfo) {
        boolean b = externalInfo.getId() != idNotProcess;
        log.info("ExternalInfoProcess.run(ExternalInfo) : {}", b);
        return b;
    }
}
