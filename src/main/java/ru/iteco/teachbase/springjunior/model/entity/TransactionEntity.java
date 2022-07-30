package ru.iteco.teachbase.springjunior.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction", schema = "bank")
public class TransactionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "source_bank_book_id", nullable = false)
    private BankBookEntity sourceBankBook;

    @ManyToOne
    @JoinColumn(name = "target_bank_book_id", nullable = false)
    private BankBookEntity targetBankBook;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "initiation_date", nullable = false)
    private LocalDateTime initiationDate;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusEntity status;
}
