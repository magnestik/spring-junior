package ru.iteco.teachbase.springjunior.account.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CurrencyValidator.class)
public @interface Currency {
    String message() default "Некорректная валюта"; //ключ ValidationMessages.properties

    Class<?>[] groups() default {}; //группа проверки

    Class<? extends Payload>[] payload() default { }; //полезная нагрузка
}