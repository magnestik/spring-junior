package ru.iteco.teachbase.springjunior.bankbook.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {
    private static final Set<String> CURRENCY = Set.of("RUB", "EUR", "USD", "GBP");

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext constraintValidatorContext) {
        return CURRENCY.contains(currency);
    }
}
