package ru.iteco.teachbase.springjunior;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.iteco.teachbase.springjunior.account.model.dto.AddressDto;
import ru.iteco.teachbase.springjunior.account.model.entity.UserEntity;
import ru.iteco.teachbase.springjunior.account.repository.UserRepository;
import ru.iteco.teachbase.springjunior.account.service.AddressService;

@Slf4j
@SpringBootTest
class SpringJuniorApplicationTests {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserRepository userRepository;

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

}

