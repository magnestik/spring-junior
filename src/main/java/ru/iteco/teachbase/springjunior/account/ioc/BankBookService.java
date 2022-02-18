package ru.iteco.teachbase.springjunior.account.ioc;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BankBookService {
    public List<BankBookInfo> getBankBook(Integer id) {
        return new ArrayList<>();
    }
}
