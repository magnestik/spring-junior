package ru.iteco.teachbase.springjunior.account.aop.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OtherRepository {
    private static final Logger LOG = LoggerFactory.getLogger(OtherRepository.class);
    
    public String getInfo(User user) {
        LOG.info("Call OtherRepository.getInfo()");
        return "OTHER_INFO";
    }

    public void saveInfo(String info) {
        if (info == null) {
            throw new RuntimeException("NULL not saved!");
        }
        LOG.info("SAVE INFO: {}", info);
    }
}
