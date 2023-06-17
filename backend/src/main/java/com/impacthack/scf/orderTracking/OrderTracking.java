package com.impacthack.scf.orderTracking;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "orderTrackings")
public class OrderTracking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long orderTrackingId;

  @Column(name = "tracking_no")
  private String trackingNo;

  @Column(name = "po_id")
  private long purchaseOrderId;

  public OrderTracking() {

  }

  public OrderTracking(String trackingNo, long purchaseOrderId) {
    this.trackingNo = trackingNo;
    this.purchaseOrderId = purchaseOrderId;
  }

  public long getOrderTrackingId() {
    return orderTrackingId;
  }

  public void setOrderTrackingId(long orderTrackingId) {
    this.orderTrackingId = orderTrackingId;
  }

  public String getTrackingNo() {
    return trackingNo;
  }

  public void setTrackingNo(String trackingNo) {
    this.trackingNo = trackingNo;
  }

  public long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "OrderTracking{" +
            "orderTrackingId=" + orderTrackingId +
            ", trackingNo='" + trackingNo + '\'' +
            ", purchaseOrderId=" + purchaseOrderId +
            '}';
  }
}
