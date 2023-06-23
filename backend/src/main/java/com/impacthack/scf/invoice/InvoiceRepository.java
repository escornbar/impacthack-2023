package com.impacthack.scf.invoice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.impacthack.scf.purchaseOrder.PurchaseOrder;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Transactional
    List<Invoice> findAll();

    @Transactional
    Optional<Invoice> findById(Long id);

    @Transactional
    Invoice findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
