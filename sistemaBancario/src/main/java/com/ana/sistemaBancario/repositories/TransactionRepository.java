package com.ana.sistemaBancario.repositories;


import com.ana.sistemaBancario.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT t FROM Transaction t WHERE t.sender.id = :accountId OR t.receiver.id = :accountId")
    List<Transaction> findAllTransactionsByAccountId(Long accountId);
}




