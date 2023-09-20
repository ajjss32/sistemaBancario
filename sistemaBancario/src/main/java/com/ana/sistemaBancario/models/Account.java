package com.ana.sistemaBancario.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number",nullable = false)
    private Long accountNumber;
    @Column(nullable = false)
    private BigDecimal balence = new BigDecimal("0.0");
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_id_client")
    private Client client;

}
