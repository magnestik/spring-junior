package ru.iteco.teachbase.springjunior.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.teachbase.springjunior.account.model.entity.AddressEntity;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
    Optional<AddressEntity> findByCountryAndCityAndStreetAndHome(String country, String city, String street, String home);
}
