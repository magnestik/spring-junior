package ru.iteco.teachbase.springjunior.account.aop.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OtherRepository {
    private static final Logger LOG = LoggerFactory.getLogger(OtherRepository.class);
    
    public String getInfo() {
        LOG.info("Call OtherRepository.getInfo()");
        return "OTHER_INFO";
    }
}
