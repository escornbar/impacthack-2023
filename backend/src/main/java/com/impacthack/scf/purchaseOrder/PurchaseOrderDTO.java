package com.impacthack.scf.purchaseOrder;

import java.util.Date;

public class PurchaseOrderDTO {

  private double total;
  private Date orderDate;
  private long supplier;
  private long distributor;

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

  public void setSupplierId(long supplier) {
    this.supplier = supplier;
  }

  public long getDistributor() {
    return distributor;
  }

  public void setDistributor(long distributor) {
    this.distributor = distributor;
  }
}
