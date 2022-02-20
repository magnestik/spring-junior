package ru.iteco.teachbase.springjunior.account.ioc;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Аннотация, которая в переменной класса будет инжектировать рандомные числа
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class IntValue {
    @InjectRandom
    private int value1;

    @InjectRandom(min = 10, max = 100)
    private int value2;

    @EncryptResult
    public String getInfo() {
        return "IntValue{" +
            "value1=" + value1 +
            ", value2=" + value2 +
            '}';
    }
}
