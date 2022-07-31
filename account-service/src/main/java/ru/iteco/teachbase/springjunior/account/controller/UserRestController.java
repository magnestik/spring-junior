package ru.iteco.teachbase.springjunior.account.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.iteco.teachbase.springjunior.account.model.dto.UserDto;
import ru.iteco.teachbase.springjunior.account.service.UserService;
import ru.iteco.teachbase.springjunior.account.validation.Created;
import ru.iteco.teachbase.springjunior.account.validation.Update;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.findById(id);
        ResponseCookie userId = ResponseCookie.from("userId", userDto.getId().toString()).maxAge(600).build();
        return ResponseEntity
            .status(HttpStatus.OK)
            .header(HttpHeaders.SET_COOKIE, userId.toString())
            .body(userDto);
    }

    @Validated(Created.class)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @Validated(Update.class)
    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.update(userDto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }
}
