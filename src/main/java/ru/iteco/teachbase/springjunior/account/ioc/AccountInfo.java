package ru.iteco.teachbase.springjunior.account.ioc;

import java.util.List;

public class AccountInfo {
    private PersonalInfo personalInfo;
    private List<BankBookInfo> bankBooks;

    public AccountInfo(PersonalInfo personalInfo, List<BankBookInfo> bankBooks) {
        this.personalInfo = personalInfo;
        this.bankBooks = bankBooks;
    }
}
