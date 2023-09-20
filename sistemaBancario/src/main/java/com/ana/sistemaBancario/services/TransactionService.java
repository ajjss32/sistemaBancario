package com.ana.sistemaBancario.services;

import com.ana.sistemaBancario.dtos.TransactionRequest;
import com.ana.sistemaBancario.dtos.TransactionResponse;
import com.ana.sistemaBancario.models.Account;
import com.ana.sistemaBancario.models.Transaction;
import com.ana.sistemaBancario.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public TransactionResponse createTransaction(TransactionRequest transactionRequest,Long idSender){
        Account sender = accountService.findAccount(idSender);
        Account receiver = accountService.findByAccountNumber(transactionRequest.getReceiverNumberAccount());
        checksAccountsAreNotEqual(sender,receiver);
        
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setReceiver(receiver);
        transaction.setSender(sender);
        
        transactionRepository.save(transaction);
        
        return modelMapper.map(transaction, TransactionResponse.class);
    }
    public TransactionResponse findTransactionById(Long idTransaction){
        Transaction transaction = transactionRepository.findById(idTransaction).orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));
        return modelMapper.map(transaction, TransactionResponse.class);
    }
    public List<TransactionResponse> findAllTransactions(Long idAccount){
        accountService.findAccount(idAccount);
        return transactionRepository.findAllTransactionsByAccountId(idAccount).stream()
                .map(transaction -> modelMapper.map(transaction, TransactionResponse.class)).toList();
    }
    public void deleteTransaction(Long idTransaction){
        Transaction transaction = transactionRepository.findById(idTransaction).orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));
        checksAccountsAreNull(transaction.getSender(),transaction.getReceiver());
        transactionRepository.delete(transaction);
    }
    public void checksAccountsAreNotEqual(Account sender,Account receiver){
        if (sender.equals(receiver)) throw new  IllegalArgumentException("Não é possível realizar transação para mesma contas");
    }
    public void checksAccountsAreNull(Account sender,Account receiver){
        if (sender!=null || receiver!=null) throw new  IllegalArgumentException("Para deletar uma necessário que ambas contas sejam nulas");
    }
    
   
}
