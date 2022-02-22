package ru.iteco.teachbase.springjunior.account.ioc;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class LazyServiceImpl implements LazyService {
    public boolean isLazy() {
        return true;
    }
}
