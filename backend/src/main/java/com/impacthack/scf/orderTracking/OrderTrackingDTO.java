package com.impacthack.scf.orderTracking;

import java.util.Date;

public class OrderTrackingDTO{


  private long orderTrackingId;
  private String trackingNo;
  private long purchaseOrder;
  private Date estimatedDeliveryDate;
  private Date actualDeliveryDate;
  private String remarks;
  private long orderTrackingStatus;

  public OrderTrackingDTO() {

  }

  public OrderTrackingDTO(String trackingNo, long purchaseOrder, Date estimatedDeliveryDate, Date actualDeliveryDate, String remarks, long orderTrackingStatus) {
    this.trackingNo = trackingNo;
    this.purchaseOrder = purchaseOrder;
    this.estimatedDeliveryDate = estimatedDeliveryDate;
    this.actualDeliveryDate = actualDeliveryDate;
    this.remarks = remarks;
    this.orderTrackingStatus = orderTrackingStatus;
  }

  public long getOrderTrackingDTOId() {
    return orderTrackingId;
  }

  public void setOrderTrackingDTOId(long orderTrackingId) {
    this.orderTrackingId = orderTrackingId;
  }

  public String getTrackingNo() {
    return trackingNo;
  }

  public void setTrackingNo(String trackingNo) {
    this.trackingNo = trackingNo;
  }

  public long getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(long purchaseOrder) {
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

  public long getOrderTrackingStatus() {
    return orderTrackingStatus;
  }

  public void setOrderTrackingStatus(long orderTrackingStatus) {
    this.orderTrackingStatus = orderTrackingStatus;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "OrderTrackingDTO{" +
            "orderTrackingId=" + orderTrackingId +
            ", trackingNo='" + trackingNo + '\'' +
            ", purchaseOrder=" + purchaseOrder +
            ", estimatedDeliveryDate=" + estimatedDeliveryDate +
            ", actualDeliveryDate=" + actualDeliveryDate +
            ", remarks='" + remarks + '\'' +
            ", orderTrackingStatus=" + orderTrackingStatus +
            '}';
  }
}
