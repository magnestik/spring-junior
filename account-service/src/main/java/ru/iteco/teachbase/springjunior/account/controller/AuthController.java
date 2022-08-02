package ru.iteco.teachbase.springjunior.account.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iteco.teachbase.springjunior.account.model.UserAuthDto;
import ru.iteco.teachbase.springjunior.account.security.jwt.JwtProvider;
import ru.iteco.teachbase.springjunior.account.service.AuthService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;

    public AuthController(AuthService authService, JwtProvider jwtProvider) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/create")
    public String createUserAuth(@RequestBody UserAuthDto userAuthDto) {
        return authService.create(userAuthDto);
    }

    @GetMapping("/login")
    public ResponseEntity<Void> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String logpass) {
        byte[] logPassDecode = Base64.getDecoder().decode(logpass.substring(6));
        String[] logPassArray = new String(logPassDecode, StandardCharsets.UTF_8).split(":");
        String login = logPassArray[0];
        String password = logPassArray[1];
        String token = jwtProvider.generateToken(login);
        return ResponseEntity
            .ok()
            .header("access_token", token)
            .build();
    }
}
