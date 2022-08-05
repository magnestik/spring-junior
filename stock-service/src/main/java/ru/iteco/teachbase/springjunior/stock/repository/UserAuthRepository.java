package ru.iteco.teachbase.springjunior.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.teachbase.springjunior.stock.model.UserAuthEntity;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Integer> {

    Optional<UserAuthEntity> findByUsername(String username);
}
