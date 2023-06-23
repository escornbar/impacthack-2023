package com.impacthack.scf.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.impacthack.scf.account.Account;
import com.impacthack.scf.invoice.Invoice;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Transactional
    List<Transaction> findAll();

    @Transactional
    Optional<Transaction> findById(Long id);

    @Transactional
    List<Transaction> findByInvoice(Invoice invoice);

    @Transactional
    List<Transaction> findByFromAccount(Account fromAccount);
    
    @Transactional
    List<Transaction> findByToAccount(Account toAccount);
}