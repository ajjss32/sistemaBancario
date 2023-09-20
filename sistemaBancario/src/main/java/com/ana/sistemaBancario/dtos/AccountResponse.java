package com.ana.sistemaBancario.dtos;

import com.ana.sistemaBancario.models.Client;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Long id;
    @NotNull
    private Long accountNumber;
    @NotNull
    private BigDecimal balence;
    @NotNull
    private Client client;

}
