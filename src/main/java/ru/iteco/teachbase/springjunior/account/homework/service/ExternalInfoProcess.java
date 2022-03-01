package ru.iteco.teachbase.springjunior.account.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.teachbase.springjunior.account.homework.model.ExternalInfo;

import java.util.Objects;

@Component
public class ExternalInfoProcess implements Process {
    private static final Logger log = LoggerFactory.getLogger(ExternalInfoProcess.class);

    @Value("${id-not-process}")
    private Integer idNotProcess;

    @Override
    public boolean run(ExternalInfo externalInfo) {
        boolean b = Objects.equals(externalInfo.getId(), idNotProcess);
        if (b) {
            throw new RuntimeException("externalInfo.id = " + idNotProcess);
        }
        log.info("ExternalInfoProcess.run(ExternalInfo): true");
        return true;
    }
}
