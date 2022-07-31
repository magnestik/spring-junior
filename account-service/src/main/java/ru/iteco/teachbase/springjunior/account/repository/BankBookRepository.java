package ru.iteco.teachbase.springjunior.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.iteco.teachbase.springjunior.account.model.entity.BankBookEntity;
import ru.iteco.teachbase.springjunior.account.model.entity.CurrencyEntity;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;

public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {

    List<BankBookEntity> findAllByUserId(Integer userId);
    boolean existsByUserIdAndNumberAndCurrency(int userId, String number, CurrencyEntity currency);

    @Transactional
    int deleteAllByUserId(int userId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("from BankBookEntity bbe where bbe.id = :id")
    Optional<BankBookEntity> lockById(@Param("id") Integer id);


    @Query("from BankBookEntity bbe where bbe.number = :number")
    @QueryHints(@QueryHint(name = HINT_CACHEABLE, value = "true"))
    Optional<BankBookEntity> findByNumber(@Param("number") Integer number);

}