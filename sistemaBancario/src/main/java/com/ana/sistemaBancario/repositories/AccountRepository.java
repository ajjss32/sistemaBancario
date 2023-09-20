package com.ana.sistemaBancario.repositories;

import com.ana.sistemaBancario.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query("SELECT a FROM Account a WHERE a.client.id = :clientId")
    List<Account> findAllByClientId(Long clientId);
    Optional<Account> findByAccountNumber(Long accountNumber);
}
