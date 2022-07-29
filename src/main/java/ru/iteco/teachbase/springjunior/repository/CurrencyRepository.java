package ru.iteco.teachbase.springjunior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.teachbase.springjunior.model.entity.CurrencyEntity;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {
    boolean existsByName(String name);

    CurrencyEntity findByName(String name);
}
