package com.impacthack.scf.payment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.impacthack.scf.invoice.Invoice;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Transactional
    List<Payment> findAll();

    @Transactional
    Optional<Payment> findById(Long id);

    @Transactional
    List<Payment> findByInvoice(Invoice invoice);
}