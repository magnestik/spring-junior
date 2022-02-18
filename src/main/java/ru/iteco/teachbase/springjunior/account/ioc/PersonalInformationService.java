package ru.iteco.teachbase.springjunior.account.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PersonalInformationService {
    @Value("${id}")
    private Integer id;

    public PersonalInfo getPersonalInfo(Integer id) {
        return new PersonalInfo(this.id);
    }
}
