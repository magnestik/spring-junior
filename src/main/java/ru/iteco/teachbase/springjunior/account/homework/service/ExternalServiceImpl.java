package ru.iteco.teachbase.springjunior.account.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.iteco.teachbase.springjunior.account.homework.annotation.CacheResult;
import ru.iteco.teachbase.springjunior.account.homework.model.ExternalInfo;
import ru.iteco.teachbase.springjunior.account.homework.annotation.PrintResult;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class ExternalServiceImpl implements ExternalService {
    private static final Logger log = LoggerFactory.getLogger(ExternalServiceImpl.class);
    private static final HashMap<Integer, ExternalInfo> externalInfoHashMap = new HashMap<>();

    @Override
    @CacheResult
    @PrintResult
    public ExternalInfo getExternalInfo(Integer id) {
        ExternalInfo externalInfo = externalInfoHashMap.get(id);
        log.info("ExternalService getExternalInfo(id = {}): {}", id, externalInfo);
        return externalInfo;
    }

    @PostConstruct
    public void init() {
        log.info("Start init() ExternalService");
        externalInfoHashMap.put(1, new ExternalInfo(1, null));
        externalInfoHashMap.put(2, new ExternalInfo(2, "hasInfo"));
        externalInfoHashMap.put(3, new ExternalInfo(3,"info"));
        externalInfoHashMap.put(4, new ExternalInfo(4,"information"));
        log.info("End init() ExternalService. externalInfoHashMap: {}", externalInfoHashMap);
    }

    @PreDestroy
    public void destroy() {
        externalInfoHashMap.clear();
        log.info("externalInfoHashMap clear: {}", externalInfoHashMap);
    }
}
