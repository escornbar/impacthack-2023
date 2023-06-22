package com.impacthack.scf.payment;

import java.util.Date;

public class PaymentDTO{


  private long paymentId;
  private long invoice;
  private Date paymentDate;
  private double paymentAmount;
  private String refNo;

  public PaymentDTO() {

  }

  public PaymentDTO(long invoice, Date paymentDate, double paymentAmount, String refNo) {
    this.invoice = invoice;
    this.paymentDate = paymentDate;
    this.paymentAmount = paymentAmount;
    this.refNo = refNo;
  }

  public long getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(long paymentId) {
    this.paymentId = paymentId;
  }

  public long getInvoice() {
    return invoice;
  }

  public void setInvoice(long invoice) {
    this.invoice = invoice;
  }

  public Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }

  public double getPaymentAmount() {
    return paymentAmount;
  }

  public void setPaymentAmount(double paymentAmount) {
    this.paymentAmount = paymentAmount;
  }

  public String getRefNo() {
    return refNo;
  }

  public void setRefNo(String refNo) {
    this.refNo = refNo;
  }
}
