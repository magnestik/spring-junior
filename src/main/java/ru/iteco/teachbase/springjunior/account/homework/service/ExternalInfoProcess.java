package ru.iteco.teachbase.springjunior.account.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.teachbase.springjunior.account.homework.annotation.CheckRequest;
import ru.iteco.teachbase.springjunior.account.homework.model.ExternalInfo;

@Component
public class ExternalInfoProcess implements Process {
    private static final Logger log = LoggerFactory.getLogger(ExternalInfoProcess.class);

    @Value("${id-not-process}")
    private Integer idNotProcess;

    @Override
    @CheckRequest
    public boolean run(ExternalInfo externalInfo) {
        log.info("ExternalInfoProcess.run(ExternalInfo): true");
        return true;
    }
}
