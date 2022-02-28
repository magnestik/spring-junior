package ru.iteco.teachbase.springjunior.account.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExternalServiceOther implements ExternalService {
    private static final Logger LOG = LoggerFactory.getLogger(ExternalServiceOther.class);

    @Override
    public String getInfo(Long id) {
        LOG.info("Call ExternalServiceOther.getInfo({})", id);
        return "OTHER_INFO with id:" + id;
    }

    @Override
    public String getCustomInfo() {
        return null;
    }
}
