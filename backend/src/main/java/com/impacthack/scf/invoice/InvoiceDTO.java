package com.impacthack.scf.invoice;

import java.util.Date;

public class InvoiceDTO {

  private double total;
  private Date issuedDate;
  private Date paymentDeadline;
  private long supplier;
  private long distributor;
  private long purchaseOrderId;
  private double downPayment;
  private double remainingPayment;

  public InvoiceDTO(double total, Date issuedDate, Date paymentDeadline, long supplier, long distributor, long purchaseOrderId, double downPayment, double remainingPayment) {
    this.total = total;
    this.issuedDate = issuedDate;
    this.paymentDeadline = paymentDeadline;
    this.supplier = supplier;
    this.distributor = distributor;
    this.purchaseOrderId = purchaseOrderId;
    this.downPayment = downPayment;
    this.remainingPayment = remainingPayment;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Date getIssuedDate() {
    return issuedDate;
  }

  public void setIssuedDate(Date issuedDate) {
    this.issuedDate = issuedDate;
  }

  public long getSupplier() {
    return supplier;
  }

  public void setSupplier(long supplier) {
    this.supplier = supplier;
  }

  public long getDistributor() {
    return distributor;
  }

  public void setDistributor(long distributor) {
    this.distributor = distributor;
  }

  public double getDownPayment() {
    return downPayment;
  }

  public void setDownPayment(double downPayment) {
    this.downPayment = downPayment;
  }

  public double getRemainingPayment() {
    return remainingPayment;
  }

  public void setRemainingPayment(double remainingPayment) {
    this.remainingPayment = remainingPayment;
  }

  public long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public Date getPaymentDeadline() {
    return paymentDeadline;
  }

  public void setPaymentDeadline(Date paymentDeadline) {
    this.paymentDeadline = paymentDeadline;
  }
}
