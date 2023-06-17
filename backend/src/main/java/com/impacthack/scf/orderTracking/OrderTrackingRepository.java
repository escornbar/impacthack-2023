package com.impacthack.scf.orderTracking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Long> {
}