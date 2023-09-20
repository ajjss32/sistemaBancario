package com.ana.sistemaBancario.dtos;

import com.ana.sistemaBancario.models.Account;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long id;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate transactionDate;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    private Account sender;
    @NotNull
    private Account receiver;
}
