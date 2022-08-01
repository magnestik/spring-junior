package ru.iteco.teachbase.springjunior.account.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.teachbase.springjunior.account.model.UserAuthDto;
import ru.iteco.teachbase.springjunior.account.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/create")
    public String createUserAuth(@RequestBody UserAuthDto userAuthDto) {
        return authService.create(userAuthDto);
    }
}
