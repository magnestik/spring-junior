package ru.iteco.teachbase.springjunior.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.teachbase.springjunior.account.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
