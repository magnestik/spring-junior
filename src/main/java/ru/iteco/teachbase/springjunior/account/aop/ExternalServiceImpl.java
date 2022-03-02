package ru.iteco.teachbase.springjunior.account.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.iteco.teachbase.springjunior.account.aop.aspect.AspectEvent;

@Component
public class ExternalServiceImpl implements ExternalService {
    private static final Logger LOG = LoggerFactory.getLogger(ExternalServiceImpl.class);

    @Override
    public String getInfo(Long id) {
        LOG.info("Call ExternalServiceImpl.getInfo({})", id);
        return "INFO with id:" + id;
    }

    @AspectEvent
    public String getCustomInfo() {
        LOG.info("Call ExternalServiceImpl.getCustomInfo");
        return "CUSTOM_INFO";
    }
}
