package ru.iteco.teachbase.springjunior.account;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ru.iteco.teachbase.springjunior.account.model.dto.AddressDto;
import ru.iteco.teachbase.springjunior.account.model.dto.ConvertResult;
import ru.iteco.teachbase.springjunior.account.model.dto.ConverterRequest;
import ru.iteco.teachbase.springjunior.account.model.dto.LatestResult;
import ru.iteco.teachbase.springjunior.account.model.entity.BankBookEntity;
import ru.iteco.teachbase.springjunior.account.model.entity.GroupEntity;
import ru.iteco.teachbase.springjunior.account.model.entity.UserEntity;
import ru.iteco.teachbase.springjunior.account.repository.BankBookRepository;
import ru.iteco.teachbase.springjunior.account.repository.GroupEntityRepository;
import ru.iteco.teachbase.springjunior.account.repository.UserRepository;
import ru.iteco.teachbase.springjunior.account.service.AddressService;
import ru.iteco.teachbase.springjunior.account.service.CurrencyServiceApi;

import java.math.BigDecimal;

@Slf4j
@SpringBootTest
class AccountApplicationTests {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupEntityRepository groupRepository;
    @Autowired
    private BankBookRepository bankBookRepository;
    @Autowired
    private CurrencyServiceApi currencyServiceApi;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    void testLoadUserFromAddress() {
        AddressDto addressDto = AddressDto.builder()
            .country("RUSSIA")
            .city("MSK")
            .street("Ленина")
            .home("12")
            .build();
        log.info(String.valueOf(addressService.getUserByAddress(addressDto)));
    }

    @Test
    @Transactional(readOnly = true)
    void testLoadUserLazyAddress() {
        UserEntity user = userRepository.findById(1).get();
        log.info("NOT ADDRESS");
        log.info("ADDRESS: {}", user.getAddress());
        log.info("BANK_BOOK: {}", user.getBankBooks());
    }

    @Test
    @Transactional
    void testLoadManyGroups() {
        UserEntity userEntity = userRepository.findById(2).get();
        log.info("USER ID: {} with GROUPS: {}", userEntity.getId(), userEntity.getGroups());
    }

    @Test
    @Transactional
    void testLoadUsersByGroups() {
        GroupEntity groupEntity = groupRepository.findById(1).get();
        log.info("GROUP ID: {} with USERS: {}", groupEntity.getId(), groupEntity.getUsers());
    }

    @Test
    @Transactional
    void testFirstCache() {
        BankBookEntity bankBookEntity1 = bankBookRepository.findById(1).get();
        bankBookEntity1.setAmount(new BigDecimal(900));
        bankBookRepository.save(bankBookEntity1);
        BankBookEntity bankBookEntity2 = bankBookRepository.findById(1).get();

        log.info("{}; {}", bankBookEntity1, bankBookEntity2);
    }

    @Test
    void testCurrencyLatestRestApi() {
        LatestResult allExchangeApi = currencyServiceApi.getAllExchangeApi();
        log.info("RESULT: {}", allExchangeApi);
        Assertions.assertThat(allExchangeApi).isNotNull().hasNoNullFieldsOrProperties();
    }

    @Test
    void testCurrencyConvertRestApi() {
        ConverterRequest converterRequest = ConverterRequest.builder().from("RUB").to("EUR").amount(BigDecimal.TEN).build();
        ConvertResult allExchangeApi = currencyServiceApi.convert(converterRequest);
        log.info("RESULT: {}", allExchangeApi);
        Assertions.assertThat(allExchangeApi).isNotNull().hasNoNullFieldsOrProperties();
    }

    @Test
    void generatePassword() {
        log.info("PASS: {}", passwordEncoder.encode("iteco"));
    }
}

