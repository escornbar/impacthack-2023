package com.impacthack.scf.loan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.impacthack.scf.account.Account;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Transactional
    List<Loan> findAll();

    @Transactional
    Optional<Loan> findById(Long id);

    @Transactional
    List<Loan> findByAccount(Account account);
}