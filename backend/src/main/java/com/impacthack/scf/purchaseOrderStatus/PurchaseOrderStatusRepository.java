package com.impacthack.scf.purchaseOrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderStatusRepository extends JpaRepository<PurchaseOrderStatus, Long> {
  // List<PurchaseOrderStatus> findByPublished(boolean published);

  // List<PurchaseOrderStatus> findByTitleContaining(String title);
}
