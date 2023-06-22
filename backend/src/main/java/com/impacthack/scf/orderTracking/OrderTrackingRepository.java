package com.impacthack.scf.orderTracking;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.impacthack.scf.tutorial.Tutorial;
import com.impacthack.scf.purchaseOrder.PurchaseOrder;


public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Long> {

    @Transactional
    List<OrderTracking> findAll();

    @Transactional
    Optional<OrderTracking> findById(Long id);

    @Transactional
    List<OrderTracking> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}