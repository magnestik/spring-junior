package ru.iteco.teachbase.springjunior.account.service;

import org.springframework.stereotype.Service;
import ru.iteco.teachbase.springjunior.account.model.dto.AddressDto;
import ru.iteco.teachbase.springjunior.account.model.entity.AddressEntity;
import ru.iteco.teachbase.springjunior.account.model.entity.UserEntity;
import ru.iteco.teachbase.springjunior.account.repository.AddressRepository;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public UserEntity getUserByAddress(AddressDto addressDto) {
        AddressEntity address = addressRepository.findByCountryAndCityAndStreetAndHome(
                addressDto.getCountry(), addressDto.getCity(), addressDto.getStreet(), addressDto.getHome()
        ).orElseThrow(() -> new RuntimeException("Адрес не найден!"));
        return address.getUser();
    }
}
