package com.ana.sistemaBancario.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    private Long receiverNumberAccount;
}
