package ru.iteco.teachbase.springjunior.account.aop.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExternalRepository {
    private static final Logger LOG = LoggerFactory.getLogger(ExternalRepository.class);

    public void setInfo(String info) {
        LOG.info("Set info in ExternalRepository. Info: {}", info);
    }
}
