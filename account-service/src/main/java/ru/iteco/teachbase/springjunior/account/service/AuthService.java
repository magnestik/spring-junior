package ru.iteco.teachbase.springjunior.account.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.iteco.teachbase.springjunior.account.model.UserAuthDto;
import ru.iteco.teachbase.springjunior.account.model.entity.UserAuthEntity;
import ru.iteco.teachbase.springjunior.account.repository.UserAuthRepository;

@Service
public class AuthService {
    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserAuthRepository userAuthRepository, PasswordEncoder passwordEncoder) {
        this.userAuthRepository = userAuthRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String create(UserAuthDto userAuthDto) {
        UserAuthEntity userAuthEntity = UserAuthEntity.builder()
            .username(userAuthDto.getLogin())
            .password(passwordEncoder.encode(userAuthDto.getPassword()))
            .build();
        return userAuthRepository.save(userAuthEntity).getUsername();
    }
}
