package com.impacthack.scf.orderTrackingStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTrackingStatusRepository extends JpaRepository<OrderTrackingStatus, Long> {
  // List<OrderTrackingStatus> findByPublished(boolean published);

  // List<OrderTrackingStatus> findByTitleContaining(String title);
}
