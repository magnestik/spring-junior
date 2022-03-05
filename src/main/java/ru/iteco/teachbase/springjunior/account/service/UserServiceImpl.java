package ru.iteco.teachbase.springjunior.account.service;

import org.springframework.stereotype.Service;
import ru.iteco.teachbase.springjunior.account.model.AddressDto;
import ru.iteco.teachbase.springjunior.account.model.UserDto;
import ru.iteco.teachbase.springjunior.account.model.UserNotFoundException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {
    private final Map<Integer, UserDto> userDtoMap = new ConcurrentHashMap<>();
    private final AtomicInteger seqId = new AtomicInteger(1);

    @PostConstruct
    public void init() {
        int id = seqId.getAndIncrement();
        userDtoMap.put(id, new UserDto(id, "Fill", "fill@mail.ru",
            new AddressDto("RUSSIA", "MOSCOW", "LENINA", "1")));
    }

    @Override
    public List<UserDto> findAll() {
        return new ArrayList<>(userDtoMap.values());
    }

    @Override
    public UserDto findById(Integer id) {
        UserDto userDto = userDtoMap.get(id);
        if (userDto == null) {
            throw new UserNotFoundException("Пользователь не найден!");
        }
        return userDto;
    }

    @Override
    public UserDto create(UserDto userDto) {
        int id = seqId.getAndIncrement();
        userDto.setId(id);
        userDtoMap.put(id, userDto);
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        UserDto userFromMap = userDtoMap.get(userDto.getId());
        if (userFromMap == null) {
            return null;
        }
        userDtoMap.put(userDto.getId(), userDto);
        return userDto;
    }

    @Override
    public void delete(Integer id) {
        userDtoMap.remove(id);
    }
}
