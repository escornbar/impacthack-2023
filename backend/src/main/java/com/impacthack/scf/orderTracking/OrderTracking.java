package com.impacthack.scf.orderTracking;

import jakarta.persistence.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.orderTrackingStatus.OrderTrackingStatus;
import com.impacthack.scf.purchaseOrder.PurchaseOrder;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "orderTrackings")
public class OrderTracking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long orderTrackingId;

  @Column(name = "tracking_no")
  private String trackingNo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="po_id")
  private PurchaseOrder purchaseOrder;

  @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "estimated_delivery_date")
  private Date estimatedDeliveryDate;

  @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "actual_delivery_date")
  private Date actualDeliveryDate;

  @Column(name = "remarks")
  private String remarks;

  @JsonIgnoreProperties({"hibernateLazyInitializer"})
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="order_tracking_status")
  private OrderTrackingStatus orderTrackingStatus;

  public OrderTracking() {

  }

  public OrderTracking(String trackingNo, PurchaseOrder purchaseOrder, Date estimatedDeliveryDate, Date actualDeliveryDate, String remarks, OrderTrackingStatus orderTrackingStatus) {
    this.trackingNo = trackingNo;
    this.purchaseOrder = purchaseOrder;
    this.estimatedDeliveryDate = estimatedDeliveryDate;
    this.actualDeliveryDate = actualDeliveryDate;
    this.remarks = remarks;
    this.orderTrackingStatus = orderTrackingStatus;
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

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public Date getEstimatedDeliveryDate() {
    return estimatedDeliveryDate;
  }

  public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
    this.estimatedDeliveryDate = estimatedDeliveryDate;
  }

  public Date getActualDeliveryDate() {
    return actualDeliveryDate;
  }

  public void setActualDeliveryDate(Date actualDeliveryDate) {
    this.actualDeliveryDate = actualDeliveryDate;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public OrderTrackingStatus getOrderTrackingStatus() {
    return orderTrackingStatus;
  }

  public void setOrderTrackingStatus(OrderTrackingStatus orderTrackingStatus) {
    this.orderTrackingStatus = orderTrackingStatus;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "OrderTracking{" +
            "orderTrackingId=" + orderTrackingId +
            ", trackingNo='" + trackingNo + '\'' +
            ", purchaseOrderId=" + purchaseOrder +
            ", estimatedDeliveryDate=" + estimatedDeliveryDate +
            ", actualDeliveryDate=" + actualDeliveryDate +
            ", remarks='" + remarks + '\'' +
            ", orderTrackingStatus=" + orderTrackingStatus +
            '}';
  }
}
