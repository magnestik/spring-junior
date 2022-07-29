package ru.iteco.teachbase.springjunior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.teachbase.springjunior.model.entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
    StatusEntity findByName(String name);
}