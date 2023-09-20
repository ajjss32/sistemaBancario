package com.ana.sistemaBancario.controllers;

import com.ana.sistemaBancario.dtos.AccountRequest;
import com.ana.sistemaBancario.dtos.AccountResponse;
import com.ana.sistemaBancario.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("{idclient}")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest,@PathVariable(value = "idclient") Long idCliente){
        AccountResponse account = accountService.createAccount(accountRequest, idCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(){
        List<AccountResponse> allAccounts = accountService.findAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }
    @GetMapping("{idaccount}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable(value = "idaccount") Long idAccount){
        AccountResponse accountResponse = accountService.findById(idAccount);
        return ResponseEntity.ok(accountResponse);
    }
    @DeleteMapping("{idaccount}")
    public void deleteAccount(@PathVariable(value = "idaccount") Long idAccount){
        accountService.deleteAccount(idAccount);
    }

}
