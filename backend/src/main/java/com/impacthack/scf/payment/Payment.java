package com.impacthack.scf.payment;

import jakarta.persistence.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.invoice.Invoice;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "payments")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long paymentId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="invoice_id")
  private Invoice invoice;

  @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "payment_date")
  private Date paymentDate;

  @Column(name = "payment_amount")
  private double paymentAmount;

  @Column(name = "ref_no")
  private String refNo;

  public Payment() {

  }

  public Payment(Invoice invoice, Date paymentDate, double paymentAmount, String refNo) {
    this.invoice = invoice;
    this.paymentDate = paymentDate;
    this.paymentAmount = paymentAmount;
    this.refNo = refNo;
  }

  public Payment(long paymentId, Date paymentDate, double paymentAmount, String refNo) {
    this.paymentId = paymentId;
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

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
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

  @java.lang.Override
  public java.lang.String toString() {
    return "Payment{" +
            "paymentId=" + paymentId +
            ", invoice=" + invoice +
            ", paymentDate=" + paymentDate +
            ", paymentAmount=" + paymentAmount +
            ", refNo='" + refNo + '\'' +
            '}';
  }
}
