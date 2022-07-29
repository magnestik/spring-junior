package ru.iteco.teachbase.springjunior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.teachbase.springjunior.model.entity.GroupEntity;

public interface GroupEntityRepository extends JpaRepository<GroupEntity, Integer> {
}