package com.impacthack.scf.invoice;

import java.util.Date;
import com.impacthack.scf.invoiceStatus.InvoiceStatus;

public class InvoiceObj {

  private long invoiceId;
  private double total;
  private Date issuedDate;
  private Date paymentDeadline;
  private InvoiceStatus invoiceStatus;
  private String invoiceFileName;
  private String invoiceFileType;
  private double downPayment;
  private double remainingPayment;
  private byte[] invoiceFileData;

  public InvoiceObj() {

  }

  public InvoiceObj(long invoiceId, double total, Date issuedDate, Date paymentDeadline, InvoiceStatus invoiceStatus,  double downPayment, double remainingPayment) {
    this.invoiceId = invoiceId;
    this.total = total;
    this.issuedDate = issuedDate;
    this.paymentDeadline = paymentDeadline;
    this.invoiceStatus = invoiceStatus;
    this.downPayment = downPayment;
    this.remainingPayment = remainingPayment;
  }

  public InvoiceObj(long invoiceId, double total, Date issuedDate, Date paymentDeadline, InvoiceStatus invoiceStatus, String invoiceFileName, String invoiceFileType, double downPayment, double remainingPayment, byte[] invoiceFileData) {
    this.invoiceId = invoiceId;
    this.total = total;
    this.issuedDate = issuedDate;
    this.paymentDeadline = paymentDeadline;
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
}