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

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private ModelMapper modelMapper;
    public TransactionResponse createTransaction(BigDecimal amount,Account sender,Account receiver){
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setReceiver(receiver);
        transaction.setSender(sender);
        transactionRepository.save(transaction);
        return modelMapper.map(transaction, TransactionResponse.class);
    }
    public TransactionResponse makeDepositToOtherAccount(TransactionRequest transactionRequest,Long idSender){
        Account sender = accountService.findAccount(idSender);
        Account receiver = accountService.findByAccountNumber(transactionRequest.getReceiverNumberAccount());

        checksAccountsAreNotEqual(sender,receiver);

        setValueTransaction(transactionRequest.getAmount(),receiver,sender);
        return createTransaction(transactionRequest.getAmount(),sender,receiver);
    }

    public TransactionResponse makeDepositToYourOwnAccount(TransactionRequest transactionRequest,Long idSender){
        Account sender = accountService.findAccount(idSender);
        if (!Objects.equals(sender.getAccountNumber(), transactionRequest.getReceiverNumberAccount())) throw new IllegalArgumentException("Para fazer esse de deposito as contas devem ser iguais");

        sender.setBalence(sender.getBalence().add(transactionRequest.getAmount()));

        return createTransaction(transactionRequest.getAmount(),sender,sender);
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
        transactionRepository.delete(transaction);
    }
    public void checksAccountsAreNotEqual(Account sender,Account receiver){
        if (sender.equals(receiver)) throw new  IllegalArgumentException("Não é possível realizar transação para mesma contas");
    }

    public void setValueTransaction(BigDecimal amount, Account reciver,Account sender){
        checkBalanceSender(sender,amount);

        reciver.setBalence(reciver.getBalence().add(amount));
        sender.setBalence(sender.getBalence().subtract(amount));
    }
    public void checkBalanceSender(Account sender,BigDecimal amount){
        if (sender.getBalence().compareTo(BigDecimal.ZERO)<=0) throw new  IllegalArgumentException("Saldo insuficiente");
        if (sender.getBalence().subtract(amount).compareTo(BigDecimal.ZERO)<0) throw new  IllegalArgumentException("Saldo insuficiente");
    }
   
}
