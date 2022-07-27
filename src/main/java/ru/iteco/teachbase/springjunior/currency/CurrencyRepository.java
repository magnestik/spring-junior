package ru.iteco.teachbase.springjunior.currency;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {
    boolean existsByName(String name);

    CurrencyEntity findByName(String name);
}
