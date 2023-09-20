package com.ana.sistemaBancario.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "transactions_date")
    private LocalDate transactionDate = LocalDate.now();
    @Column(nullable = false)
    private BigDecimal amount;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_id_sender")
    private Account sender;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_id_receiver")
    private Account receiver;
}
