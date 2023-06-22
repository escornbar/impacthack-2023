package com.impacthack.scf.invoice;
import jakarta.persistence.*;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.invoiceStatus.InvoiceStatus;
import com.impacthack.scf.purchaseOrder.PurchaseOrder;

@Entity
@Table(name = "invoices")
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long invoiceId;

  @Column(name = "total")
  private double total;

  @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "issued_date")
  private Date issuedDate;

  @JsonFormat(pattern="dd-MM-yyyy")
  @Column(name = "payment_deadline")
  private Date paymentDeadline;

  // @JsonIgnoreProperties({"hibernateLazyInitializer"})
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="po_id")
  private PurchaseOrder purchaseOrder;

  @JsonIgnoreProperties({"hibernateLazyInitializer"})
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="invoice_status")
  private InvoiceStatus invoiceStatus;

  @Column(name = "invoice_file_name")
  private String invoiceFileName;

  @Column(name = "invoice_file_type")
  private String invoiceFileType;

  @Column(name = "down_payment")
  private double downPayment;

  @Column(name = "remaining_payment")
  private double remainingPayment;

  @Lob
  @Column(name = "invoice_file_data")
  private byte[] invoiceFileData;

  public Invoice() {

  }

  public Invoice(double total, Date issuedDate, Date paymentDeadline, PurchaseOrder purchaseOrder, InvoiceStatus invoiceStatus,  double downPayment, double remainingPayment) {
    this.total = total;
    this.issuedDate = issuedDate;
    this.paymentDeadline = paymentDeadline;
    this.purchaseOrder = purchaseOrder;
    this.invoiceStatus = invoiceStatus;
    this.downPayment = downPayment;
    this.remainingPayment = remainingPayment;
  }

  public Invoice(double total, Date issuedDate, Date paymentDeadline, PurchaseOrder purchaseOrder, InvoiceStatus invoiceStatus, String invoiceFileName, String invoiceFileType, double downPayment, double remainingPayment, byte[] invoiceFileData) {
    this.total = total;
    this.issuedDate = issuedDate;
    this.paymentDeadline = paymentDeadline;
    this.purchaseOrder = purchaseOrder;
    this.invoiceStatus = invoiceStatus;
    this.invoiceFileName = invoiceFileName;
    this.invoiceFileType = invoiceFileType;
    this.downPayment = downPayment;
    this.remainingPayment = remainingPayment;
    this.invoiceFileData = invoiceFileData;
  }

  public long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(long invoiceId) {
    this.invoiceId = invoiceId;
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

  public String getInvoiceFileName() {
    return invoiceFileName;
  }

  public void setInvoiceFileName(String invoiceFileName) {
    this.invoiceFileName = invoiceFileName;
  }

  public String getInvoiceFileType() {
    return invoiceFileType;
  }

  public void setInvoiceFileType(String invoiceFileType) {
    this.invoiceFileType = invoiceFileType;
  }

  public byte[] getInvoiceFileData() {
    return invoiceFileData;
  }

  public void setInvoiceFileData(byte[] invoiceFileData) {
    this.invoiceFileData = invoiceFileData;
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

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public InvoiceStatus getInvoiceStatus() {
    return invoiceStatus;
  }

  public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
    this.invoiceStatus = invoiceStatus;
  }

  public Date getPaymentDeadline() {
    return paymentDeadline;
  }

  public void setPaymentDeadline(Date paymentDeadline) {
    this.paymentDeadline = paymentDeadline;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "Invoice{" +
            "invoiceId=" + invoiceId +
            ", total=" + total +
            ", issuedDate=" + issuedDate +
            ", paymentDeadline=" + paymentDeadline +
            ", purchaseOrder=" + purchaseOrder +
            ", invoiceStatus=" + invoiceStatus +
            ", invoiceFileName='" + invoiceFileName + '\'' +
            ", invoiceFileType='" + invoiceFileType + '\'' +
            ", downPayment=" + downPayment +
            ", remainingPayment=" + remainingPayment +
            ", invoiceFileData=" + java.util.Arrays.toString(invoiceFileData) +
            '}';
  }
}