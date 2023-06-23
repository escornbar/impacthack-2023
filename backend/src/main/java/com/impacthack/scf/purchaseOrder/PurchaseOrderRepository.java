package com.impacthack.scf.purchaseOrder;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Transactional
    List<PurchaseOrder> findAll();

    @Transactional
    Optional<PurchaseOrder> findById(Long id);
}
