package com.ana.sistemaBancario.controllers;

import com.ana.sistemaBancario.dtos.TransactionRequest;
import com.ana.sistemaBancario.dtos.TransactionResponse;
import com.ana.sistemaBancario.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction/{idaccount}")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody @Valid TransactionRequest transactionRequest
            ,@PathVariable(value = "idaccount") Long idAccount){
        TransactionResponse transaction = transactionService.createTransaction(transactionRequest, idAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(@PathVariable(value = "idaccount") Long idAccount){
        List<TransactionResponse> allTransactions = transactionService.findAllTransactions(idAccount);
        return ResponseEntity.ok(allTransactions);
    }

    @GetMapping("{idtransaction}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable(value = "idtransaction") Long idTransaction){
        TransactionResponse transaction = transactionService.findTransactionById(idTransaction);
        return ResponseEntity.ok(transaction);
    }
    @DeleteMapping("{idtransaction}")
    public void deleteTransaction(@PathVariable(value = "idtransaction") Long idTransaction){
        transactionService.deleteTransaction(idTransaction);
    }
}
