package ru.iteco.teachbase.springjunior.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.teachbase.springjunior.account.model.GroupEntity;

public interface GroupEntityRepository extends JpaRepository<GroupEntity, Integer> {
}