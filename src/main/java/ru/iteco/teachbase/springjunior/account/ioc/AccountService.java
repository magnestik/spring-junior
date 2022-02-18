package ru.iteco.teachbase.springjunior.account.ioc;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountService {
    PersonalInformationService personalInformationService;
    BankBookService bankBookService;

    public AccountService(PersonalInformationService personalInformationService, BankBookService bankBookService) {
        this.personalInformationService = personalInformationService;
        this.bankBookService = bankBookService;
    }

    public AccountInfo getAccountInfoById(Integer id){
        PersonalInfo personalInfo = personalInformationService.getPersonalInfo(id);
        List<BankBookInfo> bankBook = bankBookService.getBankBook(id);
        return new AccountInfo(personalInfo, bankBook);
    }
}
