package ru.iteco.teachbase.springjunior.account.service;

import org.springframework.stereotype.Service;
import ru.iteco.teachbase.springjunior.account.model.exception.UserNotFoundException;
import ru.iteco.teachbase.springjunior.account.model.dto.AddressDto;
import ru.iteco.teachbase.springjunior.account.model.dto.UserDto;
import ru.iteco.teachbase.springjunior.account.model.entity.AddressEntity;
import ru.iteco.teachbase.springjunior.account.model.entity.UserEntity;
import ru.iteco.teachbase.springjunior.account.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return mapUserEntitiesToDtos(userEntities);
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
            .map(this::mapUserEntityToDto)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден!"));
    }

    @Override
    public UserDto create(UserDto userDto) {
        return mapUserEntityToDto(userRepository.save(mapUserDtoToEntity(userDto)));
    }

    @Override
    public UserDto update(UserDto userDto) {
        return mapUserEntityToDto(userRepository.save(mapUserDtoToEntity(userDto)));
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    private List<UserDto> mapUserEntitiesToDtos(List<UserEntity> userEntities) {
        return userEntities.stream().map(this::mapUserEntityToDto).collect(Collectors.toList());
    }

    private UserDto mapUserEntityToDto(UserEntity userEntity) {
        return UserDto.builder()
            .id(userEntity.getId())
            .name(userEntity.getName())
            .email(userEntity.getEmail())
            .address(mapAddressEntityToDto(userEntity.getAddress()))
            .build();
    }

    private List<UserEntity> mapUserDtosToEntities(List<UserDto> userDtos) {
        return userDtos.stream().map(this::mapUserDtoToEntity).collect(Collectors.toList());
    }

    private UserEntity mapUserDtoToEntity(UserDto userDto) {
        return UserEntity.builder()
            .id(userDto.getId())
            .name(userDto.getName())
            .email(userDto.getEmail())
            .address(mapAddressDtoToEntity(userDto.getAddress()))
            .build();
    }

    private AddressDto mapAddressEntityToDto(AddressEntity addressEntity) {
        if (addressEntity == null) {
            return null;
        }
        return AddressDto.builder()
            .id(addressEntity.getId())
            .country(addressEntity.getCountry())
            .city(addressEntity.getCity())
            .street(addressEntity.getStreet())
            .home(addressEntity.getHome())
            .build();
    }

    private AddressEntity mapAddressDtoToEntity(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }
        return AddressEntity.builder()
            .id(addressDto.getId())
            .country(addressDto.getCountry())
            .city(addressDto.getCity())
            .street(addressDto.getStreet())
            .home(addressDto.getHome())
            .build();
    }

    private List<AddressDto> mapAddressEntitiesToDto(List<AddressEntity> addressEntities) {
        return addressEntities.stream().map(this::mapAddressEntityToDto).collect(Collectors.toList());
    }

    private List<AddressEntity> mapAddressDtosToEntities(List<AddressDto> addressDtos) {
        return addressDtos.stream().map(this::mapAddressDtoToEntity).collect(Collectors.toList());
    }
}
