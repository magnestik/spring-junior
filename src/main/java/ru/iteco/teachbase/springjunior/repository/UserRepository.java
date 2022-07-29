package ru.iteco.teachbase.springjunior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.teachbase.springjunior.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
