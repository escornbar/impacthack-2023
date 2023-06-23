package com.impacthack.scf.purchaseOrder;

import java.util.Date;

public class PurchaseOrderDTO {

  private double total;
  private Date orderDate;
  private long supplier;
  private long distributor;
  private long purchaseOrderStatus;

  public PurchaseOrderDTO(double total, Date orderDate, long supplier, long distributor, long purchaseOrderStatus) {
    this.total = total;
    this.orderDate = orderDate;
    this.supplier = supplier;
    this.distributor = distributor;
    this.purchaseOrderStatus = purchaseOrderStatus;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public long getSupplier() {
    return supplier;
  }

  public long getDistributor() {
    return distributor;
  }

  public void setDistributor(long distributor) {
    this.distributor = distributor;
  }

  public void setSupplier(long supplier) {
    this.supplier = supplier;
  }

  public long getPurchaseOrderStatus() {
    return purchaseOrderStatus;
  }

  public void setPurchaseOrderStatus(long purchaseOrderStatus) {
    this.purchaseOrderStatus = purchaseOrderStatus;
  }
}
