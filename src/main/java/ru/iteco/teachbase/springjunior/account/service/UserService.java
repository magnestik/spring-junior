package ru.iteco.teachbase.springjunior.account.service;

import ru.iteco.teachbase.springjunior.account.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(Integer id);
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(Integer id);
}
