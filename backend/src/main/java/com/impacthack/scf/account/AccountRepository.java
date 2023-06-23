package com.impacthack.scf.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.impacthack.scf.company.Company;



public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    List<Account> findAll();

    @Transactional
    Optional<Account> findById(Long id);

    @Transactional
    List<Account> findByOwner(Company owner);

    Optional<Account> findByAccountNumber(String accountNumber);

    @Transactional
    default void updateAccountBalance(Long accountId, double amount) {
        Optional<Account> optionalAccount = findById(accountId);
        optionalAccount.ifPresent(account -> {
            double currentBalance = account.getAccountBalance();
            double updatedBalance = currentBalance + amount;
            account.setAccountBalance(updatedBalance);
            save(account);
        });
    }
}