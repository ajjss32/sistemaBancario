package com.ana.sistemaBancario.services;

import com.ana.sistemaBancario.dtos.AccountRequest;
import com.ana.sistemaBancario.dtos.AccountResponse;
import com.ana.sistemaBancario.models.Account;
import com.ana.sistemaBancario.models.Client;
import com.ana.sistemaBancario.repositories.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ModelMapper modelMapper;

    public AccountResponse createAccount(AccountRequest accountRequest,Long idClient){
        Client client = clientService.findClient(idClient);
        Account account = new Account();
        modelMapper.map(accountRequest,account);
        account.setClient(client);
        accountRepository.save(account);
        return modelMapper.map(account,AccountResponse.class);
    }
    public AccountResponse findById(Long idAccount){
        Account account = accountRepository.findById(idAccount).orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
        return modelMapper.map(account,AccountResponse.class);
    }

    public List<AccountResponse> findAllAccounts(){
        return accountRepository.findAll().stream()
                .map(account -> modelMapper.map(account,AccountResponse.class)).toList();
    }
    public void deleteAccount(Long idAccount){
        Account account = accountRepository.findById(idAccount).orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
        accountRepository.delete(account);
    }

}
