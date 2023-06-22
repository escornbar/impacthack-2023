package com.impacthack.scf.orderItem;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.impacthack.scf.purchaseOrder.PurchaseOrder;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Transactional
    List<OrderItem> findAll();

    @Transactional
    Optional<OrderItem> findById(Long id);

    @Transactional
    List<OrderItem> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}